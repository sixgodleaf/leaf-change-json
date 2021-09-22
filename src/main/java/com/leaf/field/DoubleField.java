package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @author ycc
 */
public class DoubleField extends Field<Double> {
    protected TYPE type = TYPE.DOUBLE;

    public DoubleField() {

    }

    public DoubleField(JSONObject fieldObject) {
        super(fieldObject);
    }


    @Override
    public Double getValue(String value) {
        return Double.valueOf(value);
    }

    @Override
    public Double getValue(JSONObject value) {
        Object o = JSONPath.read(value.toJSONString(), path);
        try {
            return (Double) o;
        } catch (ClassCastException e) {
            return getValue(o.toString());
        }
    }


    @Override
    protected Double getDefault() {
        return this.defaultValue;
    }

    @Override
    protected void setDefault(String defaultValue) {
        this.defaultValue = Double.valueOf(defaultValue);
    }
}
