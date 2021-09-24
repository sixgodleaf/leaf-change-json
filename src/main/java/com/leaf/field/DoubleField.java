package com.leaf.field;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @author ycc
 */
public class DoubleField extends Field<Double> {
    protected TYPE type = TYPE.DOUBLE;

    public DoubleField() {

    }


    @Override
    public Double getValue(String value) {
        return Double.valueOf(value);
    }

    @Override
    public Double getValue(JSONObject value, String path) {
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
        this.defaultValue = StrUtil.isEmpty(defaultValue) ? 0.0 : Double.valueOf(defaultValue);
    }
}
