package online.omnia.finance_now.campaign;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lollipop on 05.07.2017.
 */
@Entity
@Table(name = "mt_token")
public class MyTargetTokenEntity extends TokenEntity{
    public MyTargetTokenEntity() {
    }

    public MyTargetTokenEntity(int idAccount, String token, Date timeCreate, Date timeExpired, Date timeRenew) {
        super(idAccount, token, timeCreate, timeExpired, timeRenew);
    }
}
