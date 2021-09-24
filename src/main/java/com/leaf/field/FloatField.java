package com.leaf.field;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @author ycc
 */
public class FloatField extends Field<Float> {
    protected TYPE type = TYPE.FLOAT;

    public FloatField() {

    }

    @Override
    public Float getValue(String value) {
        return Float.valueOf(value);
    }
    @Override
    public Float getValue(JSONObject value, String path) {
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
        this.defaultValue = StrUtil.isEmpty(defaultValue) ? 0F : Float.valueOf(defaultValue);
    }
}
