package online.omnia.finance_now.campaign;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lollipop on 05.07.2017.
 */
@Entity
@Table(name = "cheetah_token")
public class CheetahTokenEntity extends TokenEntity{
    public CheetahTokenEntity() {
    }

    public CheetahTokenEntity(int idAccount, String token, Date timeCreate, Date timeExpired, Date timeRenew) {
        super(idAccount, token, timeCreate, timeExpired, timeRenew);
    }
}
