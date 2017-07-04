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
public class CheetahAds implements BaseNetwork{
    private HttpMethodUtils cheetahMethods;
    private Map<String, String> headersMap;
    final static Logger logger = Logger.getLogger(CheetahAds.class);

    public CheetahAds(String baseURL, String tokenKey) {
        cheetahMethods = new HttpMethodUtils("https://api.ori.cmcm.com/");
        headersMap = new HashMap<String, String>();
        headersMap.put("Accept", "application/json,application/x.orion.v1+json");
        headersMap.put("Authorization", "Bearer access_token");

    }
    @Override
    public String getUserBalance() {
        try {
            String answer = cheetahMethods.getMethod("user/balance", headersMap);
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
