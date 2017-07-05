package online.omnia.finance_stat.networks;


import online.omnia.finance_stat.utils.HttpMethodUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lollipop on 04.07.2017.
 */
public abstract class BaseNetwork {
    private HttpMethodUtils methods;
    private Map<String, String> headersMap;

    public BaseNetwork(String baseURL) {
        methods = new HttpMethodUtils(baseURL);
        headersMap = new HashMap<String, String>();

    }
    public abstract String getUserBalance();
    public abstract Integer getAccountId();
    public abstract String getToken(String clientId, String clientKey);
    public String getCurrency() {
        return "USD";
    }

    public HttpMethodUtils methods() {
        return methods;
    }

    public Map<String, String> getHeadersMap() {
        return headersMap;
    }
}
