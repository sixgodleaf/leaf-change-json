package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @author ycc
 */
public class ShortField extends Field<Short> {
    protected TYPE type = TYPE.FLOAT;
    protected Short defaultValue;

    public ShortField() {

    }
    public ShortField(JSONObject fieldObject) {
        super(fieldObject);
    }

    @Override
    public Short getValue(String value) {
        return Short.valueOf(value);
    }

    @Override
    public Short getValue(JSONObject value) {
        Object o = JSONPath.read(value.toJSONString(), path);
        try {
            return (Short) o;
        } catch (ClassCastException e) {
            return getValue(o.toString());
        }
    }

    @Override
    protected Short getDefault() {
        return defaultValue;
    }

    @Override
    protected void setDefault(String defaultValue) {
        this.defaultValue = Short.valueOf(defaultValue);
    }


}
