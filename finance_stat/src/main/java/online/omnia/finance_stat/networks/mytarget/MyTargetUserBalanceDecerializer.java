package online.omnia.finance_stat.networks.mytarget;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;

/**
 * Created by lollipop on 05.07.2017.
 */
public class MyTargetUserBalanceDecerializer implements JsonDeserializer<Double>{
    final static Logger logger = Logger.getLogger(MyTargetUserBalanceDecerializer.class);
    @Override
    public Double deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Double balance = null;
        try {
            JsonObject object = jsonElement.getAsJsonObject();
            balance = object.get("balance").getAsDouble();
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return balance;
    }
}
