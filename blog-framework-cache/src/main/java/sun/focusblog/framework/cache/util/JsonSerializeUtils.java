package sun.focusblog.framework.cache.util;

import com.google.gson.*;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by yamorn on 2015/11/11.
 * <p/>
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
     * Convert json array to list
     *
     * @param jsonArray json array string
     * @param type      element type
     * @param <T>       element class type
     * @return list
     */
    public static <T> List<T> toList(String jsonArray, Class<T> type) {
        if (StringUtils.isEmpty(jsonArray)) {
            return null;
        }
        List<T> list = new LinkedList<>();
        try {
            JsonArray array = (JsonArray) new JsonParser().parse(jsonArray);
            for (int i = 0; i < array.size(); i++) {
                JsonObject jsonObject = (JsonObject) array.get(i);
                T t = gson.fromJson(jsonObject.toString(), type);
                list.add(t);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
