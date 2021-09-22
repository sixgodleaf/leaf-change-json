package com.leaf.field;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.BooleanUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * 日期处理方式
 * @author ycc
 */
public class BooleanField extends Field<Boolean> {
    protected TYPE type = TYPE.BOOLEAN;

    private String format;

    public BooleanField() {

    }
    public BooleanField(JSONObject fieldObject) {
        super(fieldObject);
    }


    @Override
    public Boolean getValue(String value) {
        return Boolean.valueOf(value);
    }

    @Override
    public Boolean getValue(JSONObject value) {
        Object o = JSONPath.read(value.toJSONString(), path);
        try {
            return (Boolean) o;
        } catch (ClassCastException e) {
            return getValue(o.toString());
        }
    }

    @Override
    protected Boolean getDefault() {
        return this.defaultValue;
    }

    @Override
    protected void setDefault(String defaultValue) {
        this.defaultValue = Boolean.valueOf(defaultValue);
    }
}
