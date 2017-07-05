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
    public MyTarget(String baseURL) {
        super(baseURL);
        getHeadersMap().put("Content-Type", "application/x-www-form-urlencoded");
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
        return null;
    }

    @Override
    public Integer getAccountId() {
        return null;
    }



}
