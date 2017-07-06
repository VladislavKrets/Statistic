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

    public CheetahAds(String baseURL, String clientId, String clientCredentials) {
        super(baseURL, clientId, clientCredentials);
        getHeadersMap().put("Accept", "application/json,application/x.orion.v1+json");

    }
    public CheetahTokenEntity updateToken(){
        try {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            nameValuePairList.add(new BasicNameValuePair("grant_type", "client_credentials"));
            nameValuePairList.add(new BasicNameValuePair("client_id", getClientId()));
            nameValuePairList.add(new BasicNameValuePair("client_secret", getClientCredentials()));
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
    public String getCurrentToken() {
        if (getHeadersMap().containsKey("Authorization")) return getHeadersMap().get("Authorization");
        return null;
    }

    @Override
    public String type() {
        return "cheetahads";
    }

    @Override
    public Double getUserBalance() {
        try {
            String answer = methods().getMethod("user/balance", getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Double.class, new CheetahAdsUserBalanceDeserializer());
            Gson gson = builder.create();
            return gson.fromJson(answer, Double.class);
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
