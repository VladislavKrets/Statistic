package online.omnia.finance_now.networks.cheetah;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;

/**
 * Created by lollipop on 05.07.2017.
 */
public class CheetahAdsTokenDeserializer implements JsonDeserializer<TokenEntity>{
    final static Logger logger = Logger.getLogger(CheetahAdsTokenDeserializer.class);
    @Override
    public TokenEntity deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        String status = object.get("status").getAsString();
        String message = object.get("message").getAsString();

        logger.debug(String.format("Status=%s Message=%s", (status.equals("200") ? "9999" : status), message));

        if (status.equals("200")) {
            TokenEntity entity = new TokenEntity(
                    object.get("data").getAsJsonObject().get("access_token").getAsString(),
                    object.get("data").getAsJsonObject().get("token_type").getAsString(),
                    object.get("data").getAsJsonObject().get("expires_in").getAsString()
            );
            return entity;
        }
        return null;
    }
}
