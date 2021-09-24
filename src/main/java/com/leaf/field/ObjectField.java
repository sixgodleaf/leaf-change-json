package com.leaf.field;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycc
 */
public class ObjectField extends Field<Map<String, Object>> {
    protected TYPE type = TYPE.OBJECT;

    public ObjectField() {

    }
    public ObjectField(JSONObject fieldObject) {
        super(fieldObject);
    }


    @Override
    public Map<String, Object> getValue(String value) {
        return null;
    }

    @Override
    public Map<String, Object> getValue(JSONObject value) {
        Map<String, Field> fieldMap = getInterFields();
        Map<String, Object> results = new HashMap<>();
        JSONObject jsonObject = value.getJSONObject(path);
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
    protected Map<String, Object> getDefault() {
        return null;
    }

    @Override
    protected void setDefault(String defaultValue) {

    }
}
