package online.omnia.finance_now.networks.cheetah;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import online.omnia.finance_now.networks.BaseNetwork;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by lollipop on 03.07.2017.
 */
public class CheetahAds extends BaseNetwork{

    final static Logger logger = Logger.getLogger(CheetahAds.class);

    public CheetahAds(String baseURL, String tokenKey) {
        super(baseURL, tokenKey);

    }
    @Override
    public String getUserBalance() {
        try {
            String answer = getCheetahMethods().getMethod("user/balance", getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(String.class, new CheetahAdsUserBalanceDeserializer());
            Gson gson = builder.create();
            return gson.fromJson(answer, String.class);
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }

    @Override
    public Integer getAccountId() {
        try {
            String answer = getCheetahMethods().getMethod("user/info", getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Integer.class, new CheetahAdsUserIdDeserializer());
            Gson gson = builder.create();
            return gson.fromJson(answer, Integer.class);

        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return -1;
    }

    @Override
    public String getCurrency() {
        return null;
    }

}
