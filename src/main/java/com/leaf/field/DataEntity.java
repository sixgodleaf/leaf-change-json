package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {}
 *
 * @Author: yechengcheng
 * @Date: 2021/9/18 11:35
 */
@Data
public class DataEntity {
    public Map<String, Field> fieldMap = new HashMap<>();
    private JSONObject jsonObject;
    private String JSONString;


    public void setJSONString(String jsonString) {
        this.JSONString = jsonString;
        jsonObject = JSONObject.parseObject(jsonString);
    }
    public void setJslt(String jslt) {
        fieldMap = FieldParser.parse(JSONObject.parseObject(jslt));
    }

    /**
     * 生成Map
     * @return
     */
    public Map<String, Object> changeMap() {
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

    public <T> T get(String key) {
       return (T) fieldMap.get(key).fieldExecute(jsonObject, jsonObject);
    }
    /**
     * 生成JSONObject
     * @return
     */
    public JSONObject changeJSONObject() {
        Map<String, Object> results = new HashMap<>();
        for (String key : fieldMap.keySet()) {
            results.put(key, fieldMap.get(key).fieldExecute(jsonObject, jsonObject));
        }
        return new JSONObject(results);
    }



    public static void main(String[] args) {

        DataEntity dataEntity = new DataEntity();
        List<Map<String,Object>> integer = dataEntity.get("abc");

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

    }

}
