package online.omnia.finance_stat.outer;


import online.omnia.finance_stat.campaign.CheetahTokenEntity;
import online.omnia.finance_stat.networks.cheetah.CheetahAds;
import online.omnia.finance_stat.networks.cheetah.CheetahAdsTokenDeserializer;
import online.omnia.finance_stat.omniaDB.MySQLDAOImpl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by lollipop on 03.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        Broker broker = new Broker();
        broker.collect();
    }

}
