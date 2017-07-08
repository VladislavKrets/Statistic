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
    @Column(name = "client_id", length = 50)
    private String clientId;
    @Column(name = "client_secret", length = 200)
    private String clientSecret;
    @Column(name = "token_table_name", length = 50)
    private String tokenTableName;
    @Column(name = "api_key", length = 200)
    private String apiKey;
    @Column(name = "username", length = 50)
    private String username;
    @Column(name = "type", length = 10)
    private String type;
    @Column(name = "password", length = 50)
    private String password;
    @Column(name = "api_URL", length = 1000)
    private String apiURL;
    @Column(name = "name", length = 200)
    private String name;

    public Account() {
    }

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

    public String getName() {
        return name;
    }
}
