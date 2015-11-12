package sun.focusblog.framework.cache.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by yamorn on 2015/11/11.
 *
 * A simple Json Serializer.
 */
public class JsonSerializeUtils {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static Gson gson = null;

    static {
        gson = new GsonBuilder().setDateFormat(DATE_PATTERN).create();
    }

    /**
     * Try to serialize value to json object.
     */
    public static String toJsonString(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String && StringUtils.isEmpty((String) value)) {
            return "";
        }
        return gson.toJson(value);
    }

    /**
     * Convert json string to object
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return gson.fromJson(json, clazz);
    }

    /**
     * Valid json string
     */
    public static boolean isGoodJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
        } catch (JsonSyntaxException e) {
            return false;
        }
        return true;
    }

}
