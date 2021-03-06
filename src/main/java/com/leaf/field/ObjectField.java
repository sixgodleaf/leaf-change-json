package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycc
 */
public class ObjectField extends Field<Map<String, Object>> {
    protected TYPE type = TYPE.OBJECT;

    public ObjectField() {

    }

    public Map<String, Object> getValue(String value) {
        return new Gson().fromJson(value, Map.class);
    }

    @Override
    public Map<String, Object> getValue(JSONObject value, String path) {
        Map<String, Field> fieldMap = getInterFields();
        Map<String, Object> results = new HashMap<>();
        JSONObject jsonObject = (JSONObject) JSONPath.read(value.toJSONString(), path);
        for (String key : fieldMap.keySet()) {
            results.put(key, fieldMap.get(key).fieldExecute(value, jsonObject));
        }
        return results;
    }

    @Override
    public void setFieldJSONObject(JSONObject fieldObject) {
        super.setFieldJSONObject(fieldObject);
        Map<String, Field> interDataTypeMap = FieldParser.parse(fieldObject.getJSONObject("interFields"));
        this.setInterFields(interDataTypeMap);
    }

    @Override
    public Map<String, Object> getValue(Object value) {
        return getValue(value.toString());
    }


    @Override
    protected Map<String, Object> getDefault() {
        return null;
    }

    @Override
    protected void setDefault(String defaultValue) {

    }
}
