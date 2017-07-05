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

    public BaseNetwork(String baseURL) {
        cheetahMethods = new HttpMethodUtils(baseURL);
        headersMap = new HashMap<String, String>();

    }
    public abstract String getUserBalance();
    public abstract Integer getAccountId();
    public String getCurrency() {
        return "USD";
    }

    public HttpMethodUtils getCheetahMethods() {
        return cheetahMethods;
    }

    public Map<String, String> getHeadersMap() {
        return headersMap;
    }
}
