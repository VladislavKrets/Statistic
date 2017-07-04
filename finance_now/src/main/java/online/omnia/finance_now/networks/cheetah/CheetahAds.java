package online.omnia.finance_now.networks.cheetah;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import online.omnia.finance_now.networks.BaseNetwork;
import online.omnia.finance_now.utils.HttpMethodUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public int getAccountId() {
        return 0;
    }

    @Override
    public String getCurrency() {
        return null;
    }

}
