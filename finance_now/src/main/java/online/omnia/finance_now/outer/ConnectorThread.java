package online.omnia.finance_now.outer;

import online.omnia.finance_now.campaign.Account;
import online.omnia.finance_now.networks.BaseNetwork;
import online.omnia.finance_now.networks.cheetah.CheetahAds;
import online.omnia.finance_now.networks.mytarget.MyTarget;
import online.omnia.finance_now.omniaDB.MySQLDAOImpl;
import online.omnia.finance_now.utils.FinanceNow;
import online.omnia.finance_now.utils.TokenAdder;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lollipop on 06.07.2017.
 */
public class ConnectorThread implements Runnable{
    private CountDownLatch countDownLatch;
    private MySQLDAOImpl mySQLDAO;
    private BaseNetwork network;

    public ConnectorThread(CountDownLatch countDownLatch, BaseNetwork network) {
        this.countDownLatch = countDownLatch;
        this.network = network;
        mySQLDAO = MySQLDAOImpl.getInstance();
    }

    @Override
    public void run() {
        if (network instanceof MyTarget) TokenAdder.tokenChangeMT((MyTarget) network);
        else if (network instanceof CheetahAds) TokenAdder.tokenChangeCheetah((CheetahAds) network);
        FinanceNow financeNow = new FinanceNow(network.getAccountId(), new Date(),
                Double.parseDouble(network.getUserBalance()), network.getCurrency());
        mySQLDAO.addFinance(financeNow);
        countDownLatch.countDown();
    }
}
