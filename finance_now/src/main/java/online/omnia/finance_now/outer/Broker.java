package online.omnia.finance_now.outer;

import online.omnia.finance_now.networks.BaseNetwork;
import online.omnia.finance_now.networks.cheetah.CheetahAds;
import online.omnia.finance_now.networks.mytarget.MyTarget;
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
    private List<BaseNetwork> networks;
    final static Logger logger = Logger.getLogger(Broker.class);
    public Broker() {
        networks = new ArrayList<>();
        BaseNetwork network = new CheetahAds("https://api.ori.cmcm.com/");
        String token = network.getToken("13256", "ae3a27715fb432f9ba036f163354e598");
        networks.add(network);
        network = new MyTarget("https://target.my.com/api/v2/");
        token = network.getToken("6qW93na1vpBBX9O7",
                "SSAipEtQb7vkNaEgmix5gIVll0cjP3eW7roj5uGJ5G04sEaVyRcOWxvPXhwHa5CBSoG4BgbTNGCw4ROMBIXTVePapdN3iWNNM2vvyU0geaKhtidXwkAsJNc8gF2X3dJmekKxNJGY8XtU6dEADkHRsMUIG4Gz4ovRdMVBfuMTF7G1z4QN1sJJLdybEUoyFnCyhrEifIDWygcsmKxHCWGpXzOqUFyg");
        networks.add(network);

    }

    public void collect() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(networks.size());
        for (int i = 0; i < networks.size(); i++) {

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.debug(e.getMessage());
        }
        executor.shutdown();

    }
}
