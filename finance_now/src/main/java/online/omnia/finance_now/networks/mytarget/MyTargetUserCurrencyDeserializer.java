package online.omnia.finance_now.networks.mytarget;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;

/**
 * Created by lollipop on 05.07.2017.
 */
public class MyTargetUserCurrencyDeserializer implements JsonDeserializer<String>{
    final static Logger logger = Logger.getLogger(MyTargetUserCurrencyDeserializer.class);
    @Override
    public String deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            JsonObject object = jsonElement.getAsJsonObject();
            String currency = object.get("currency").getAsString();
            return currency;
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return null;
    }
}
