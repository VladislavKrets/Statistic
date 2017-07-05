package online.omnia.finance_stat.networks.cheetah;

/**
 * Created by lollipop on 05.07.2017.
 */
public class CheetahTokenEntity {
    private String accessToken;
    private String tokenType;
    private String expiresIn;

    public CheetahTokenEntity(String accessToken, String tokenType, String expiresIn) {
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

    public String getExpiresIn() {
        return expiresIn;
    }
}
