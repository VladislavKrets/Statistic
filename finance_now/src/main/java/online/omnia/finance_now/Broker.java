package online.omnia.finance_now;

import online.omnia.finance_now.networks.BaseNetwork;
import online.omnia.finance_now.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lollipop on 04.07.2017.
 */
public class Broker {
    private List<BaseNetwork> networks;
    public Broker() {
        networks = new ArrayList<>();
        List<Class> classes = Utils.getFilesInPackage(BaseNetwork.class);

    }
}
