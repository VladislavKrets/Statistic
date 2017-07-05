package online.omnia.finance_now.outer;

import online.omnia.finance_now.networks.BaseNetwork;
import online.omnia.finance_now.networks.cheetah.CheetahAds;
import online.omnia.finance_now.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lollipop on 04.07.2017.
 */
public class Broker {
    private List<BaseNetwork> networks;
    public Broker() {
        networks = new ArrayList<>();
        BaseNetwork network = new CheetahAds("https://api.ori.cmcm.com/");
        String token = ((CheetahAds) network).getAccessToken("13256", "ae3a27715fb432f9ba036f163354e598").getAccessToken();
        networks.add(new CheetahAds("https://api.ori.cmcm.com/"));

    }
}
