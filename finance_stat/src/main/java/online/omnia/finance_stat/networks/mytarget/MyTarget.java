package online.omnia.finance_stat.networks.mytarget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import online.omnia.finance_stat.networks.BaseNetwork;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lollipop on 05.07.2017.
 */
public class MyTarget extends BaseNetwork {
    final static Logger logger = Logger.getLogger(MyTarget.class);
    public MyTarget(String baseURL) {
        super(baseURL);
        getHeadersMap().put("Content-Type", "application/x-www-form-urlencoded");
        getHeadersMap().put("Authorization", "Bearer AfknWkWlUUBaCwdVe9PW9KukpFeW2u2DDNdUqmU03DkiY1iYD48ByxM8oaIB2dOX2TJctp1Ms79gFx9jRUUkOg7tdCrs0UmYKP3XtbfFULaFFxKR3q6znqirFXySXIEHN7sMaH3RMmsVdV2T7RY0msdk9mjFQ48t7DFfvEcacHape3OuRzYxqU0zvmSkfhpG8NlcU5guonhiY4eQycy3r9PfQKxfqZ0tdGJxI7I1SeMLQ");

    }

    public MyTargetTokenEntity getAccessToken(String clientId, String clientSecret) {
        try {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            nameValuePairList.add(new BasicNameValuePair("grant_type", "client_credentials"));
            nameValuePairList.add(new BasicNameValuePair("client_id", clientId));
            nameValuePairList.add(new BasicNameValuePair("client_secret", clientSecret));
            String answer = methods().postMethod("oauth2/token.json", nameValuePairList, getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(MyTargetTokenEntity.class, new MyTargetTokenDeserializer());
            Gson gson = builder.create();
            MyTargetTokenEntity entity = gson.fromJson(answer, MyTargetTokenEntity.class);
            getHeadersMap().put("Authorization",
                    String.format("%s %s", entity.getTokenType(), entity.getAccessToken()));
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
            String answer = methods().getMethod("user/account.json", getHeadersMap());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(String.class, new MyTargetUserBalanceDecerializer());
            Gson gson = builder.create();
            return gson.fromJson(answer, String.class);
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
