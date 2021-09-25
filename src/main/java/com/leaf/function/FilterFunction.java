package com.leaf.function;

import com.alibaba.fastjson.JSONObject;

/**
 * @created by ycc
 * @since 2021-09-23
 */
public class FilterFunction extends Function {
    private Value value;

    @Override
    public void setParam(String expression) {
        value = new Value(expression, path);
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T call(JSONObject root, Object object) {
        return (T) value.call(root, object);
    }

    @Override
    public <T> T call(JSONObject root, JSONObject object) {
        return (T) value.call(root, object);
    }
}
