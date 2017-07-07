package online.omnia.finance_stat.networks.mytarget;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;

/**
 * Created by lollipop on 05.07.2017.
 */
public class MyTargetUserIdDeserializer implements JsonDeserializer<Integer>{
    final Logger logger = Logger.getLogger(MyTargetUserIdDeserializer.class);
    @Override
    public Integer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        Integer id = null;
        try {
            JsonObject object = jsonElement.getAsJsonObject();
            id = object.get("id").getAsInt();

        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return id;
    }
}
