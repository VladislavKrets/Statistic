package online.omnia.finance_now.networks.cheetah;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import online.omnia.finance_now.networks.BaseNetwork;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lollipop on 03.07.2017.
 */
public class CheetahAds extends BaseNetwork{

    final static Logger logger = Logger.getLogger(CheetahAds.class);

    public CheetahAds(String baseURL) {
        super(baseURL);
        getHeadersMap().put("Accept", "application/json,application/x.orion.v1+json");
        //getHeadersMap().put("Authorization", "Bearer " + tokenKey);

    }
    public CheetahTokenEntity getAccessToken(String clientId, String clientCredential){
        try {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            nameValuePairList.add(new BasicNameValuePair("grant_type", "client_credentials"));
            nameValuePairList.add(new BasicNameValuePair("client_id", clientId));
            nameValuePairList.add(new BasicNameValuePair("client_secret", clientCredential));
            String answer = methods().postMethod("oauth/access_token", nameValuePairList, getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(CheetahTokenEntity.class, new CheetahAdsTokenDeserializer());
            Gson gson = builder.create();
            CheetahTokenEntity entity = gson.fromJson(answer, CheetahTokenEntity.class);
            getHeadersMap().put("Authorization", String.format("%s %s", entity.getTokenType(), entity.getAccessToken()));
            return entity;
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }

    @Override
    public String getToken(String clientId, String clientKey) {
        if (getHeadersMap().containsKey("Authorization")) return getHeadersMap().get("Authorization");
        return getAccessToken(clientId, clientKey).getAccessToken();
    }

    @Override
    public String getUserBalance() {
        try {
            String answer = methods().getMethod("user/balance", getHeadersMap());
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
            String answer = methods().getMethod("user/info", getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Integer.class, new CheetahAdsUserIdDeserializer());
            Gson gson = builder.create();
            return gson.fromJson(answer, Integer.class);

        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return -1;
    }

}
