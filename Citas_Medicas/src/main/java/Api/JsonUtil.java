package Api;
/**
 *
 * @author cesar
 */
import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonUtil {

public static String toJson(Object TheObject) {
    return new Gson().toJson(TheObject);
}
public static ResponseTransformer json() {
    return Api.JsonUtil::toJson;
    
}
}