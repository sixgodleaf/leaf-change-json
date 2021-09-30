package com.leaf.function;

import com.alibaba.fastjson.JSONObject;

/**
 * larger(3)
 *
 * @created by ycc
 * @since 2021-09-22
 */
public class LargerFunction extends Function {
    private Double value = 0.0;
    private Value endValue;

    @Override
    public void setParam(String param) {
        String[] params = param.split(",");
        endValue = new Value(params[0].trim(), this.path);
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T call(JSONObject root, Object object) {
        Boolean b = Double.valueOf(object.toString()) > Double.valueOf(endValue.execute(root, root));
        return (T) b;
    }

    @Override
    public <T> T call(JSONObject root, JSONObject object) {
        Boolean b = object.size() > Double.valueOf(endValue.execute(root, root));
        return (T) b;
    }
}
