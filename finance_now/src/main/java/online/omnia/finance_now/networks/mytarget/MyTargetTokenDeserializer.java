package online.omnia.finance_now.networks.mytarget;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;

/**
 * Created by lollipop on 05.07.2017.
 */
public class MyTargetTokenDeserializer implements JsonDeserializer<MyTargetTokenEntity>{
    final static Logger logger = Logger.getLogger(MyTargetTokenDeserializer.class);
    @Override
    public MyTargetTokenEntity deserialize(JsonElement jsonElement, Type type,
                                           JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();

        try {
            MyTargetTokenEntity entity = new MyTargetTokenEntity(
                    object.get("access_token").getAsString(),
                    object.get("token_type").getAsString(),
                    object.get("expires_in").getAsInt() * 1000L,
                    object.get("refresh_token").getAsString(),
                    object.get("tokens_left").getAsInt()
            );
            return entity;
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return null;
    }
}
