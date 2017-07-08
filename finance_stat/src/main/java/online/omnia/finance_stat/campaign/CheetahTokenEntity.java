package online.omnia.finance_stat.campaign;

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

    public CheetahTokenEntity(int idAccount, String token, String tokenType, Date timeCreate, Date timeExpired) {
        super(idAccount, token, tokenType, timeCreate, timeExpired);
    }
}
