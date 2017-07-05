package online.omnia.finance_now.networks.mytarget;

import online.omnia.finance_now.networks.BaseNetwork;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;


/**
 * Created by lollipop on 05.07.2017.
 */
public class MyTarget extends BaseNetwork{

    public MyTarget(String baseURL, String tokenKey) {
        super(baseURL);
    }

    @Override
    public String getUserBalance() {
        return null;
    }

    @Override
    public Integer getAccountId() {
        return null;
    }

    public String signEncoder(String requestMethod, String requestUrl, String requestBody){
        String result = "";
        try {
            String signature = URLEncoder.encode(requestMethod, "UTF-8") + "&" + URLEncoder.encode(requestUrl, "UTF-8") + "&" + URLEncoder.encode(requestBody, "UTF-8");
            Key signingKey = new SecretKeySpec("SSAipEtQb7vkNaEgmix5gIVll0cjP3eW7roj5uGJ5G04sEaVyRcOWxvPXhwHa5CBSoG4BgbTNGCw4ROMBIXTVePapdN3iWNNM2vvyU0geaKhtidXwkAsJNc8gF2X3dJmekKxNJGY8XtU6dEADkHRsMUIG4Gz4ovRdMVBfuMTF7G1z4QN1sJJLdybEUoyFnCyhrEifIDWygcsmKxHCWGpXzOqUFyg"
                    .getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(signature.getBytes());
            result = Base64.encodeBase64String(rawHmac);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return result;
    }
}
