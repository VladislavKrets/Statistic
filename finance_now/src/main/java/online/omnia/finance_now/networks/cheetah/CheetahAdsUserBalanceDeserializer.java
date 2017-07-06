package online.omnia.finance_now.networks.cheetah;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;

/**
 * Created by lollipop on 03.07.2017.
 */
public class CheetahAdsUserBalanceDeserializer implements JsonDeserializer<Double>{
    final static Logger logger = Logger.getLogger(CheetahAdsUserBalanceDeserializer.class);
    @Override
    public Double deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject balanceObject = jsonElement.getAsJsonObject();
        String status = balanceObject.get("status").getAsString();

        String message = balanceObject.get("message").getAsString();

        logger.debug(String.format("Status=%s Message=%s", (status.equals("200") ? "9999" : "200"), message));

        if (status.equals("200")) {
            return balanceObject.get("data").getAsJsonObject().get("balance").getAsDouble();
        }

        return null;
    }
}
