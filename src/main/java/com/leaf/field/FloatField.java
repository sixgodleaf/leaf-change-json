package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @author ycc
 */
public class FloatField extends Field<Float> {
    protected TYPE type = TYPE.FLOAT;

    public FloatField() {

    }
    public FloatField(JSONObject fieldObject) {
        super(fieldObject);
    }

    @Override
    public Float getValue(String value) {
        return Float.valueOf(value);
    }
    @Override
    public Float getValue(JSONObject value) {
        Object o = JSONPath.read(value.toJSONString(), path);
        try {
            return (Float) o;
        } catch (ClassCastException e) {
            return getValue(o.toString());
        }
    }

    @Override
    protected Float getDefault() {
        return this.defaultValue;
    }

    @Override
    protected void setDefault(String defaultValue) {
        this.defaultValue = Float.valueOf(defaultValue);
    }
}
