package online.omnia.finance_now.campaign;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lollipop on 04.07.2017.
 */
@Entity
@Table(name = "info_account")
public class AccountEntity {
    @Id
    @Column(name = "id_account", length = 11)
    private int idAccount;
    @Column(name = "id_info_account", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idInfoAccount;
    @Column(name = "api_key", length = 50)
    private String apiKey;
    @Column(name = "username", length = 50)
    private String userName;
    @Column(name = "type", length = 10)
    private String type;
    @Column(name = "password", length = 50)
    private String password;
    @Column(name = "api_URL", length = 1000)
    private String apiURL;
    @OneToOne
    @JoinColumn(name = "idAccount")
    private Account account;
    public AccountEntity() {
    }

    public AccountEntity(int idAccount, String apiKey, String userName, String type, String password, String apiURL) {
        this.idAccount = idAccount;
        this.apiKey = apiKey;
        this.userName = userName;
        this.type = type;
        this.password = password;
        this.apiURL = apiURL;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public int getIdInfoAccount() {
        return idInfoAccount;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUserName() {
        return userName;
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

    public Account getAccount() {
        return account;
    }
}
