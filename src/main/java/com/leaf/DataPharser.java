package com.leaf;

import com.alibaba.fastjson.JSONObject;
import com.leaf.field.Field;
import com.leaf.field.FieldParser;
import com.leaf.field.TYPE;

import java.util.HashMap;
import java.util.Map;

/**
 * @created by ycc
 * @since 2021-09-25
 */
public class DataPharser {
    public Map<String, Field> fieldMap = new HashMap<>();


    public DataPharser(String jslt) {
        fieldMap = FieldParser.parse(JSONObject.parseObject(jslt));
    }


    public void setJslt(String jslt) {
        fieldMap = FieldParser.parse(JSONObject.parseObject(jslt));
    }

    /**
     * 生成Map
     * @return
     */
    public Map<String, Object> changeMap(JSONObject jsonObject) {
        Map<String, Object> results = new HashMap<>();
        for (String key : fieldMap.keySet()) {
            Field field = fieldMap.get(key);
            if (TYPE.MAP.equals(field.type)) {
                Map<String, Object> objectMap = (Map<String, Object>) field.fieldExecute(jsonObject, jsonObject);
                results.putAll(objectMap);
            } else {
                results.put(key, field.fieldExecute(jsonObject, jsonObject));
            }
        }
        return results;
    }

    /**
     * 生成JSONObject
     * @return
     */
    public JSONObject changeJSONObject(JSONObject jsonObject) {
        Map<String, Object> results = new HashMap<>();
        for (String key : fieldMap.keySet()) {
            results.put(key, fieldMap.get(key).fieldExecute(jsonObject, jsonObject));
        }
        return new JSONObject(results);
    }


    /**
     * 增加一种配置的类型，支持配置中使用
     * @param keyword
     * @param field
     */
    public void configField(String keyword, Field field) {
        FieldParser.addField(keyword, field);
    }

    /**
     * 增加一种配置的函数，支持配置中使用
     * @param keyword
     * @param field
     */
    public void configFunction(String keyword, Field field) {
        FieldParser.addField(keyword, field);
    }

    public void addField(String keyword, Field field) {
        fieldMap.put(keyword, field);
    }

}
