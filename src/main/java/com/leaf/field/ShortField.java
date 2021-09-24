package com.leaf.field;

import cn.hutool.core.util.StrUtil;
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

    @Override
    public Short getValue(String value) {
        return Short.valueOf(value);
    }

    @Override
    public Short getValue(JSONObject value, String path) {
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
        this.defaultValue = StrUtil.isEmpty(defaultValue) ? 0 : Short.valueOf(defaultValue);
    }


}
