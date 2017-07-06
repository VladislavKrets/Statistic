package online.omnia.finance_now.campaign;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lollipop on 05.07.2017.
 */
@MappedSuperclass
public abstract class TokenEntity {
    @Id
    @Column(name = "id_account", length = 11)
    private int idAccount;
    @Column(name = "token", length = 50)
    private String token;
    @Column(name = "time_create", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreate;
    @Column(name = "time_expired", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeExpired;
    @Column(name = "time_renew", columnDefinition = "DATETIME")
    private Date timeRenew;


    public TokenEntity() {
    }

    public TokenEntity(int idAccount, String token, Date timeCreate, Date timeExpired, Date timeRenew) {
        this.idAccount = idAccount;
        this.token = token;
        this.timeCreate = timeCreate;
        this.timeExpired = timeExpired;
        this.timeRenew = timeRenew;
    }

    public String getToken() {
        return token;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public Date getTimeExpired() {
        return timeExpired;
    }

    public Date getTimeRenew() {
        return timeRenew;
    }

    public int getIdAccount() {
        return idAccount;
    }
}
