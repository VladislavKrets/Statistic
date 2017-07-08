package online.omnia.finance_stat.outer;

import online.omnia.finance_stat.networks.BaseNetwork;
import online.omnia.finance_stat.networks.cheetah.CheetahAds;
import online.omnia.finance_stat.networks.mytarget.MyTarget;
import online.omnia.finance_stat.omniaDB.MySQLDAOImpl;
import online.omnia.finance_stat.utils.FinanceStat;
import online.omnia.finance_stat.utils.TokenAdder;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lollipop on 06.07.2017.
 */
public class ConnectorThread implements Runnable{
    private CountDownLatch countDownLatch;
    private MySQLDAOImpl mySQLDAO;
    private BaseNetwork network;
    final static Logger logger = Logger.getLogger(ConnectorThread.class);

    public ConnectorThread(CountDownLatch countDownLatch, BaseNetwork network) {
        this.countDownLatch = countDownLatch;
        this.network = network;
        mySQLDAO = MySQLDAOImpl.getInstance();
    }

    @Override
    public void run() {
        if (network instanceof MyTarget){
            TokenAdder.tokenChangeMT((MyTarget) network);
        }
        else if (network instanceof CheetahAds){
         TokenAdder.tokenChangeCheetah((CheetahAds) network);
        }
        Integer accountId = network.getAccountId();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
           date = dateFormat.parse(dateFormat.format(date));

        } catch (ParseException e) {
            logger.debug(e.getMessage());

        }
        Double balance = network.getUserBalance();

        String currency = network.getCurrency();

        FinanceStat financeStat = new FinanceStat(accountId, date,
                balance, currency);
        mySQLDAO.addFinance(financeStat);
        countDownLatch.countDown();
    }
}
