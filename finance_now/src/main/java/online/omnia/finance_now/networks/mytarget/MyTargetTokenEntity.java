package online.omnia.finance_now.networks.mytarget;

/**
 * Created by lollipop on 05.07.2017.
 */
public class MyTargetTokenEntity {
    private String accessToken;
    private String tokenType;
    private String expiresIn;
    private String refreshToken;
    private int tokens_left;

    public MyTargetTokenEntity(String accessToken, String tokenType, String expiresIn, String refreshToken, int tokens_left) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.tokens_left = tokens_left;
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public int getTokens_left() {
        return tokens_left;
    }
}
