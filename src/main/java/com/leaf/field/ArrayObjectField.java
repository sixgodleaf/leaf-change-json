package com.leaf.field;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期处理方式
 * @author ycc
 */
public class ArrayObjectField extends Field<List<Map<String, Object>>> {
    protected TYPE type = TYPE.ARRAYOBJECT;

    public ArrayObjectField() {

    }
    public ArrayObjectField(JSONObject fieldObject) {
        super(fieldObject);
        Map<String, Field> interDataTypeMap = FieldParser.parse(fieldObject.getJSONObject("interFields"));
        this.setInterFields(interDataTypeMap);
    }


    @Override
    public List<Map<String, Object>> getValue(String value) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getValue(JSONObject value) {
        List<Map<String, Object>> results = new ArrayList<>();
        Map<String, Field> fieldMap = getInterFields();
        JSONArray jsonArray = (JSONArray) JSONPath.read(value.toJSONString(), path);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map<String, Object> singleMap = new HashMap<>();
            for (String key : fieldMap.keySet()) {
                singleMap.put(key, fieldMap.get(key).fieldExecute(value, jsonObject));
            }
            results.add(singleMap);
        }
        return results;
    }

    @Override
    protected List<Map<String, Object>> getDefault() {
        return null;
    }

    @Override
    protected void setDefault(String defaultValue) {

    }
}
