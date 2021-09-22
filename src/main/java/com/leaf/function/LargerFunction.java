package com.leaf.function;

import com.alibaba.fastjson.JSONObject;

/**
 * larger(3, keyword)
 *
 * @created by ycc
 * @since 2021-09-22
 */
public class LargerFunction extends Function {
    private Double value = 0.0;
    private String keyword;

    @Override
    public void setExpression(String expression) {
        int start = expression.indexOf("(");
        int end = expression.lastIndexOf(")");
        String[] params = expression.substring(start + 1, end).split(",");
        value = Double.valueOf(params[0].trim());
        keyword = params.length > 1 ? params[1].trim() : null;
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T call(JSONObject root, Object object) {
        Boolean b = Double.valueOf(object.toString()) > value;
        return (T) b;
    }

    @Override
    public <T> T call(JSONObject root, JSONObject object) {
        if (object.getDouble(keyword) > value) {
            return (T) object;
        } else {
            return null;
        }
    }

}
