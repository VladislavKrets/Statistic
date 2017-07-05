package online.omnia.finance_now.campaign;

import online.omnia.finance_now.omniaDB.MySQLDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

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
    @Column(name = "token_table_name", length = 50)
    private String tokenTableName;
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

    private TokenEntity tokenEntity;
    public AccountEntity() {
    }

    public AccountEntity(int idAccount, String tokenTableName, String apiKey, String userName, String type, String password, String apiURL) {
        this.idAccount = idAccount;
        this.tokenTableName = tokenTableName;
        this.apiKey = apiKey;
        this.userName = userName;
        this.type = type;
        this.password = password;
        this.apiURL = apiURL;
    }

    public TokenEntity getTokenEntity() {
        switch (tokenTableName){
            case "mt_token": {
                SessionFactory sessionFactory = MySQLDAOImpl.getSessionFactory();
                Session session = sessionFactory.openSession();
                Query query = session.createQuery("select from MyTargetTokenEntity where idAccount=" + idAccount, MyTargetTokenEntity.class);
                TokenEntity entity = (TokenEntity) query.getSingleResult();
                session.close();
                tokenEntity = entity;
                return entity;
            }
            case "cheetah_token": {
                SessionFactory sessionFactory = MySQLDAOImpl.getSessionFactory();
                Session session = sessionFactory.openSession();
                Query query = session.createQuery("select from CheetahTokenEntity where idAccount=" + idAccount, CheetahTokenEntity.class);
                TokenEntity entity = (TokenEntity) query.getSingleResult();
                session.close();
                tokenEntity = entity;
                return entity;
            }
        }
        return tokenEntity;
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

    public String getTokenTableName() {
        return tokenTableName;
    }
}
