package online.omnia.finance_stat.campaign;

import javax.persistence.*;

/**
 * Created by lollipop on 04.07.2017.
 */
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "client_secret")
    private String clientSecret;
    @Column(name = "token_table_name")
    private String tokenTableName;
    @Column(name = "api_key")
    private String apiKey;
    @Column(name = "username")
    private String username;
    @Column(name = "type")
    private String type;
    @Column(name = "password")
    private String password;
    @Column(name = "api_URL")
    private String apiURL;

    public int getAccountId() {
        return accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getTokenTableName() {
        return tokenTableName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public String getApiURL() {
        return apiURL;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", tokenTableName='" + tokenTableName + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                ", password='" + password + '\'' +
                ", apiURL='" + apiURL + '\'' +
                '}';
    }
}
