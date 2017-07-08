package online.omnia.finance_now.outer;

import online.omnia.finance_now.campaign.Account;
import online.omnia.finance_now.networks.BaseNetwork;
import online.omnia.finance_now.networks.cheetah.CheetahAds;
import online.omnia.finance_now.networks.mytarget.MyTarget;
import online.omnia.finance_now.omniaDB.MySQLDAOImpl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lollipop on 04.07.2017.
 */
public class Broker {
    final static Logger logger = Logger.getLogger(Broker.class);
    private List<BaseNetwork> networks;
    private MySQLDAOImpl mySQLDAO;
    public Broker() {
        mySQLDAO = MySQLDAOImpl.getInstance();
        networks = getAllNetworks();
    }

    private List<BaseNetwork> getAllNetworks(){
        List<BaseNetwork> networks = new ArrayList<>();
        List<Account> accounts = mySQLDAO.getAccounts();

        BaseNetwork network;
        for (Account account : accounts) {
            switch (account.getType()) {
                case "mytarget": {
                    network = new MyTarget(account.getApiURL(), account.getClientId(),
                            account.getClientSecret(), account.getAccountId());
                    networks.add(network);
                    break;
                }
                case "cheetah": {
                    String baseURL = account.getApiURL();
                    if (!baseURL.startsWith("https://")) baseURL = "https://" + baseURL;
                    if (!baseURL.endsWith("/")) baseURL = baseURL + "/";
                    network = new CheetahAds(baseURL, account.getClientId(),
                            account.getClientSecret(), account.getAccountId());
                    networks.add(network);
                    break;
                }
                case "facebook": break;
                case "adwords": break;
            }
        }
        return networks;
    }

    public void collect() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(networks.size());
        for (BaseNetwork network : networks) {
            executor.submit(new ConnectorThread(countDownLatch, network));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.debug(e.getMessage());
        }
        executor.shutdown();

    }
}
