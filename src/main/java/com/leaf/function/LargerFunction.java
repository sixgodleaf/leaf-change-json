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
    private Value endValue;

    @Override
    public void setParam(String param) {
        String[] params = param.split(",");
        endValue = new Value(params[0].trim(), this.path);
        keyword = params.length > 1 ? params[1].trim() : null;
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
