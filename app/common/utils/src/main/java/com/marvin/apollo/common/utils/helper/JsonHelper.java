package com.marvin.apollo.common.utils.helper;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author hufeng
 * @version JsonHelper.java, v 0.1 2019-01-27 01:00 Exp $
 */

public class JsonHelper {
    @SuppressWarnings("unchecked")
    public static JSONObject getJsonFromMap(Map<String, Object> map) throws JSONException {
        JSONObject jsonData = new JSONObject();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Map<?, ?>) {
                value = getJsonFromMap((Map<String, Object>) value);
            }
            jsonData.put(key, value);
        }
        return jsonData;
    }
}
