package online.omnia.finance_now.networks.cheetah;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by lollipop on 05.07.2017.
 */
public class CheetahTokenEntity {
    private String accessToken;
    private String tokenType;
    private long expiresIn;

    public CheetahTokenEntity(String accessToken, String tokenType, long expiresIn) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    @Override
    public String toString() {
        return "CheetahTokenEntity{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                '}';
    }
}
