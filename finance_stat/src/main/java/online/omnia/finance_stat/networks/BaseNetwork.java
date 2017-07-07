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
    private String clientId;
    private String clientCredentials;
    private int idAccount;

    public BaseNetwork(String baseURL, String clientId, String clientCredentials, int accountId) {
        methods = new HttpMethodUtils(baseURL);
        headersMap = new HashMap<String, String>();
        this.clientId = clientId;
        this.clientCredentials = clientCredentials;
        this.idAccount = accountId;

    }
    public abstract Double getUserBalance();
    public abstract Object updateToken();
    public abstract Integer getAccountId();
    public abstract String getCurrentToken();
    public abstract String type();

    public int getIdAccount() {
        return idAccount;
    }

    public String getCurrency() {
        return "USD";
    }

    public HttpMethodUtils methods() {
        return methods;
    }

    public Map<String, String> getHeadersMap() {
        return headersMap;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientCredentials() {
        return clientCredentials;
    }
}
