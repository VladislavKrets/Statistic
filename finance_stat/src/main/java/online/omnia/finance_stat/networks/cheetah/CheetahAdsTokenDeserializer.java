package online.omnia.finance_stat.networks.cheetah;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by lollipop on 05.07.2017.
 */
public class CheetahAdsTokenDeserializer implements JsonDeserializer<CheetahTokenEntity>{
    final static Logger logger = Logger.getLogger(CheetahAdsTokenDeserializer.class);
    @Override
    public CheetahTokenEntity deserialize(JsonElement jsonElement, Type type,
                                          JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        String status = object.get("status").getAsString();
        String message = object.get("message").getAsString();

        logger.debug(String.format("Status=%s Message=%s", (status.equals("200") ? "9999" : status), message));

        if (status.equals("200")) {
            CheetahTokenEntity entity = new CheetahTokenEntity(
                    object.get("data").getAsJsonObject().get("access_token").getAsString(),
                    object.get("data").getAsJsonObject().get("token_type").getAsString(),
                    object.get("data").getAsJsonObject().get("expires_in").getAsInt() * 1000L
            );
            return entity;
        }
        return null;
    }
}
