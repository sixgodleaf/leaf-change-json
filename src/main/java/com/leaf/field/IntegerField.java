package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @author ycc
 */
public class IntegerField extends Field<Integer> {
    protected TYPE type = TYPE.INT;

    public IntegerField() {

    }

    @Override
    public Integer getValue(Object value) {
        return getValue(value.toString());
    }


    public Integer getValue(String value) {
        return Integer.valueOf(value);
    }

    @Override
    public Integer getValue(JSONObject value, String path) {
        Object o = JSONPath.read(value.toJSONString(), path);
        try {
            return getValue(o.toString());
        } catch (ClassCastException e) {
            return getValue(o.toString());
        }
    }

    @Override
    protected Integer getDefault() {
        return this.defaultValue;
    }

    @Override
    protected void setDefault(String defaultValue) {
        this.defaultValue = defaultValue == null ? 0 : Integer.valueOf(defaultValue);
    }
}
