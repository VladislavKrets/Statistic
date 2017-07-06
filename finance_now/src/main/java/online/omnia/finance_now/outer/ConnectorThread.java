package online.omnia.finance_now.outer;

import online.omnia.finance_now.campaign.Account;
import online.omnia.finance_now.networks.BaseNetwork;
import online.omnia.finance_now.networks.cheetah.CheetahAds;
import online.omnia.finance_now.omniaDB.MySQLDAOImpl;
import online.omnia.finance_now.utils.FinanceNow;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lollipop on 06.07.2017.
 */
public class ConnectorThread implements Runnable{
    private CountDownLatch countDownLatch;
    private MySQLDAOImpl mySQLDAO;
    private BaseNetwork network;
    private List<Account> accounts;

    public ConnectorThread(CountDownLatch countDownLatch, BaseNetwork network, List<Account> accounts) {
        this.countDownLatch = countDownLatch;
        this.network = network;
        this.accounts = accounts;
        mySQLDAO = MySQLDAOImpl.getInstance();
    }

    @Override
    public void run() {

        FinanceNow financeNow = new FinanceNow(network.getAccountId(), new Date(),
                Double.parseDouble(network.getUserBalance()), network.getCurrency());
        mySQLDAO.addFinance(financeNow);
        countDownLatch.countDown();
    }
}
