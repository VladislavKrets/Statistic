package online.omnia.finance_now.campaign.cheetah;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import online.omnia.finance_now.utils.HttpMethodUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by lollipop on 03.07.2017.
 */
public class CheetahAds {
    private HttpMethodUtils cheetahMethods;
    private Map<String, String> headersMap;

    public CheetahAds() {
        cheetahMethods = new HttpMethodUtils("https://api.ori.cmcm.com/");
        headersMap = new HashMap<String, String>();
        headersMap.put("Accept", "application/json,application/x.orion.v1+json");
        headersMap.put("Authorization", "Bearer access_token");

    }

    public String getUserBalance() throws IOException {
        String answer = cheetahMethods.getMethod("user/balance", headersMap);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(String.class, new CheetahAdsUserBalanceDeserializer());
        Gson gson = builder.create();
        return gson.fromJson(answer, String.class);
    }
}
