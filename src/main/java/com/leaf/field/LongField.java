package com.leaf.field;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.Data;

/**
 * @author ycc
 */
@Data
public class LongField extends Field<Long> {
    protected TYPE type = TYPE.LONG;


    public LongField() {

    }

    @Override
    public Long getValue(String value) {
        return Long.valueOf(value);
    }

    @Override
    public Long getValue(JSONObject value, String path) {
        Object o = JSONPath.read(value.toJSONString(), path);
        try {
            return (Long) o;
        } catch (ClassCastException e) {
            return getValue(o.toString());
        }
    }

    @Override
    protected Long getDefault() {
        return this.defaultValue;
    }

    @Override
    protected void setDefault(String defaultValue) {
        this.defaultValue = StrUtil.isEmpty(defaultValue) ? 0L : Long.valueOf(defaultValue);
    }
}
