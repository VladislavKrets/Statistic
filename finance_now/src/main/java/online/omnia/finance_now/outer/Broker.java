package online.omnia.finance_now.outer;

import online.omnia.finance_now.campaign.Account;
import online.omnia.finance_now.networks.BaseNetwork;
import online.omnia.finance_now.networks.cheetah.CheetahAds;
import online.omnia.finance_now.networks.mytarget.MyTarget;
import online.omnia.finance_now.omniaDB.MySQLDAOImpl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lollipop on 04.07.2017.
 */
public class Broker {
    private Map<BaseNetwork, List<Account>> baseNetworkListMap;
    final static Logger logger = Logger.getLogger(Broker.class);
    private MySQLDAOImpl mySQLDAO;
    public Broker() {
        mySQLDAO = MySQLDAOImpl.getInstance();
        baseNetworkListMap = new HashMap<>();
        fillNetworkListMap();


    }

    private void fillNetworkListMap() {
        List<BaseNetwork> networks = getAllNetworks();
        List<Account> accounts = mySQLDAO.getAccounts();
        List<Account> accountTempList;
        for (BaseNetwork baseNetwork : networks) {
            accountTempList = new ArrayList<>();
            for (Account account : accounts) {
                if (baseNetwork.type().equals(account.getAccountEntity().getType())) accountTempList.add(account);
            }
            baseNetworkListMap.put(baseNetwork, accountTempList);
        }
    }

    private List<BaseNetwork> getAllNetworks(){
        List<BaseNetwork> networks = new ArrayList<>();
        BaseNetwork network = new CheetahAds("https://api.ori.cmcm.com/");
        String token = network.getToken("13256", "ae3a27715fb432f9ba036f163354e598");
        networks.add(network);
        network = new MyTarget("https://target.my.com/api/v2/");
        token = network.getToken("6qW93na1vpBBX9O7",
                "SSAipEtQb7vkNaEgmix5gIVll0cjP3eW7roj5uGJ5G04sEaVyRcOWxvPXhwHa5CBSoG4BgbTNGCw4ROMBIXTVePapdN3iWNNM2vvyU0geaKhtidXwkAsJNc8gF2X3dJmekKxNJGY8XtU6dEADkHRsMUIG4Gz4ovRdMVBfuMTF7G1z4QN1sJJLdybEUoyFnCyhrEifIDWygcsmKxHCWGpXzOqUFyg");
        networks.add(network);
        return networks;
    }

    public void collect() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(baseNetworkListMap.size());
        for (Map.Entry<BaseNetwork, List<Account>> entry : baseNetworkListMap.entrySet()) {
            executor.submit(new ConnectorThread(countDownLatch, entry.getKey(), entry.getValue()));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.debug(e.getMessage());
        }
        executor.shutdown();

    }
}
