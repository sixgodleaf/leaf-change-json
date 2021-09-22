package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.List;

/**
 * 日期处理方式
 * @author ycc
 */
public class ArrayField extends Field<List> {
    protected TYPE type = TYPE.ARRAY;

    public ArrayField(JSONObject fieldObject) {
        super(fieldObject);
    }



    @Override
    public List getValue(String value) {
        return null;
    }

    @Override
    public List getValue(JSONObject value) {
        Object o = JSONPath.read(value.toJSONString(), path);
        try {
            return (List) o;
        } catch (ClassCastException e) {
            return getValue(o.toString());
        }
    }

    @Override
    protected List getDefault() {
        return null;
    }

    @Override
    protected void setDefault(String defaultValue) {

    }
}
