package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @author ycc
 */
public class StringField extends Field<String> {
    protected TYPE type = TYPE.STRING;

    public StringField() {

    }

    @Override
    public String getValue(Object value) {
        return value.toString();
    }

    public String getValue(String value) {
        return value;
    }

    @Override
    public String getValue(JSONObject value, String path) {
        Object o = JSONPath.read(value.toJSONString(), path);
        try {
            return (String) o;
        } catch (ClassCastException e) {
            return o.toString();
        }
    }

    @Override
    protected String getDefault() {
        return this.defaultValue;
    }

    @Override
    protected void setDefault(String defaultValue) {
        this.defaultValue = defaultValue;
    }

}
