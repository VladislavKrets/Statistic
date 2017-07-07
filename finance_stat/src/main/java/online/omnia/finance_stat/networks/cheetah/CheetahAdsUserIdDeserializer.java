package online.omnia.finance_stat.networks.cheetah;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;

/**
 * Created by lollipop on 04.07.2017.
 */
public class CheetahAdsUserIdDeserializer implements JsonDeserializer<Integer>{
    final static Logger logger = Logger.getLogger(CheetahAdsUserIdDeserializer.class);
    @Override
    public Integer deserialize(JsonElement jsonElement, Type type,
                               JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject userObject = jsonElement.getAsJsonObject();
        String status = userObject.get("status").getAsString();
        String message = userObject.get("message").getAsString();

        logger.debug(String.format("Status=%s Message=%s", (status.equals("200") ? "9999" : status), message));

        if (status.equals("200")) {
            Integer id = userObject.get("data").getAsJsonObject().get("id").getAsInt();
            return id;
        }
        return -1;
    }
}
