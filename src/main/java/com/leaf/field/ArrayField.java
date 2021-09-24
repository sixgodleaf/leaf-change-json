package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.List;

/**
 * @author ycc
 */
public class ArrayField extends Field<List<Object>> {
    protected TYPE type = TYPE.ARRAY;

    public ArrayField() {

    }



    @Override
    public List getValue(String value) {
        return null;
    }

    @Override
    public List getValue(JSONObject value, String path) {
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
