package com.utils.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json转Object
 * Created by GSN on 2017/4/1.
 *
 * @author 郭少男
 */
public class FormatObject {

    /**
     * json -> Object
     *
     * @param jsonStr     json字符串
     * @param entityClass 实体类
     * @return Object
     */
    public Object stringConversion(String jsonStr, Class entityClass) {
        return JSONObject.toBean(JSONObject.fromObject(jsonStr), entityClass);
    }

    /**
     * array + json -> map
     *
     * @param str  数组字符串里放json字符串的String 格式要求:[{"key":"value"},{"key2":"value2"}]
     * @param keys 要取出的key值
     * @return map
     */
    public List<Map<String, String>> stringConversion(String str, String[] keys) {

        List<Map<String, String>> list = new ArrayList<>();

        JSONArray jsonArray = JSONArray.fromObject(str);


        for (int i = 0; i < jsonArray.size(); i++) {

            Map<String, String> map = new HashMap<>(8);
            JSONObject jo = JSONObject.fromObject(jsonArray.getJSONObject(i));

            for (String key : keys) {
                map.put(key, jo.getString(key));
            }

            list.add(map);

        }
        return list;
    }

}
