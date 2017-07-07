package online.omnia.finance_now.networks.mytarget;

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
 * Created by lollipop on 05.07.2017.
 */
public class MyTarget extends BaseNetwork{
    final static Logger logger = Logger.getLogger(MyTarget.class);
    private String refreshToken = "vmtYJVtfR6bX3kp6g9KTlOVy3gm5IWtxfk1pSOZDWixlPAeztwDb5kbB6aEI45qQ5LEf0XXTeYNlvGqHCwzuSsqG0KbW9FUFMzzRUArjbSUfqz00a9eBG9I24JGLoiR6YYhXWzwyj6FHQvNeTlAkt5hPBvIFpNRZuaUGGiM5GHcmTwbZNXmWu4pAQfqZATDr6FzQCjVxQUm53vCPH3ONiwkxhyQpaKJEWozQEMOCl6J5qmUfJ1";
    public MyTarget(String baseURL, String clientId, String clientCredentials, int idAccount) {
        super(baseURL, clientId, clientCredentials, idAccount);
        getHeadersMap().put("Content-Type", "application/x-www-form-urlencoded");
        getHeadersMap().put("Authorization", "Bearer AfknWkWlUUBaCwdVe9PW9KukpFeW2u2DDNdUqmU03DkiY1iYD48ByxM8oaIB2dOX2TJctp1Ms79gFx9jRUUkOg7tdCrs0UmYKP3XtbfFULaFFxKR3q6znqirFXySXIEHN7sMaH3RMmsVdV2T7RY0msdk9mjFQ48t7DFfvEcacHape3OuRzYxqU0zvmSkfhpG8NlcU5guonhiY4eQycy3r9PfQKxfqZ0tdGJxI7I1SeMLQ");

    }

    public MyTargetTokenEntity updateToken() {
        try {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            nameValuePairList.add(new BasicNameValuePair("grant_type", "client_credentials"));
            if (refreshToken != null) {
                nameValuePairList.add(new BasicNameValuePair("grant_type", "refresh_token"));
                nameValuePairList.add(new BasicNameValuePair("refresh_token", refreshToken));
            }

            nameValuePairList.add(new BasicNameValuePair("client_id", getClientId()));
            nameValuePairList.add(new BasicNameValuePair("client_secret", getClientCredentials()));
            String answer = methods().postMethod("oauth2/token.json", nameValuePairList, getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(MyTargetTokenEntity.class, new MyTargetTokenDeserializer());
            Gson gson = builder.create();
            MyTargetTokenEntity entity = gson.fromJson(answer, MyTargetTokenEntity.class);
            getHeadersMap().put("Authorization",
                    String.format("%s %s", entity.getTokenType(), entity.getAccessToken()));
            refreshToken = entity.getRefreshToken();
            logger.debug("Refresh token: " + refreshToken);
            return entity;
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }
    public void setAccessToken(MyTargetTokenEntity entity) {
        getHeadersMap().put("Authorization", String.format("%s &s",
                entity.getTokenType(), entity.getAccessToken()));
        refreshToken = entity.getRefreshToken();
    }
    @Override
    public String getCurrentToken() {
        if (getHeadersMap().containsKey("Authorization")) return getHeadersMap().get("Authorization");
        return null;
    }

    @Override
    public String type() {
        return "mytarget";
    }

    @Override
    public Double getUserBalance() {
        try {
            String answer = methods().getMethod("user/account.json", getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Double.class, new MyTargetUserBalanceDecerializer());
            Gson gson = builder.create();
            return gson.fromJson(answer, Double.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getAccountId() {
        try {
            String answer = methods().getMethod("user.json", getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Integer.class, new MyTargetUserIdDeserializer());
            Gson gson = builder.create();
            return gson.fromJson(answer, Integer.class);
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }

    @Override
    public String getCurrency() {
        try {
            String answer = methods().getMethod("user.json", getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(String.class, new MyTargetUserCurrencyDeserializer());
            Gson gson = builder.create();
            return gson.fromJson(answer, String.class);
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }

}
