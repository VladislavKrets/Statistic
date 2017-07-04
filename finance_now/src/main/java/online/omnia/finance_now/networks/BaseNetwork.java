package online.omnia.finance_now.networks;

import online.omnia.finance_now.utils.HttpMethodUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lollipop on 04.07.2017.
 */
public abstract class BaseNetwork {
    private HttpMethodUtils cheetahMethods;
    private Map<String, String> headersMap;

    public BaseNetwork(String baseURL, String tokenKey) {
        cheetahMethods = new HttpMethodUtils(baseURL);
        headersMap = new HashMap<String, String>();
        headersMap.put("Accept", "application/json,application/x.orion.v1+json");
        headersMap.put("Authorization", "Bearer " + tokenKey);

    }
    public abstract String getUserBalance();
    public abstract Integer getAccountId();
    public abstract String getCurrency();

    public HttpMethodUtils getCheetahMethods() {
        return cheetahMethods;
    }

    public Map<String, String> getHeadersMap() {
        return headersMap;
    }
}
