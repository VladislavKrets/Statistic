package online.omnia.finance_now.networks.mytarget;

/**
 * Created by lollipop on 05.07.2017.
 */
public class MyTargetTokenEntity {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
    private String refreshToken;
    private int tokens_left;

    public MyTargetTokenEntity(String accessToken, String tokenType, long expiresIn, String refreshToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public int getTokens_left() {
        return tokens_left;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "MyTargetTokenEntity{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", tokens_left=" + tokens_left +
                '}';
    }
}
