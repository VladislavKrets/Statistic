package online.omnia.finance_now.networks.mytarget;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;

/**
 * Created by lollipop on 05.07.2017.
 */
public class MyTargetUserBalanceDecerializer implements JsonDeserializer<String>{
    final static Logger logger = Logger.getLogger(MyTargetUserBalanceDecerializer.class);
    @Override
    public String deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String balance = null;
        try {
            JsonObject object = jsonElement.getAsJsonObject();
            balance = object.get("balance").getAsString();
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return balance;
    }
}
