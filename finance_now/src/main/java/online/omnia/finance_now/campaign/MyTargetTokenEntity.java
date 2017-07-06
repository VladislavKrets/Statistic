package online.omnia.finance_now.campaign;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lollipop on 05.07.2017.
 */
@Entity
@Table(name = "mt_token")
public class MyTargetTokenEntity extends TokenEntity{
    @Column(name = "scope")
    private String scope;
    @Column(name = "refresh_token")
    private String refreshToken;
    public MyTargetTokenEntity() {
    }

    public MyTargetTokenEntity(int idAccount, String tokenType, String token, Date timeCreate, Date timeExpired, Date timeRenew, String scope, String refreshToken) {
        super(idAccount, token, tokenType, timeCreate, timeExpired, timeRenew);
        this.scope = scope;
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
