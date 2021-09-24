package com.leaf.function;

import com.alibaba.fastjson.JSONObject;
import com.leaf.Value;

/**
 * larger(3, keyword)
 *
 *
 *
 * @created by ycc
 * @since 2021-09-22
 */
public class LargerFunction extends Function {
    private Double value = 0.0;
    private String keyword;
    private Value<Double> startValue;
    private Value<Double> endValue;

    @Override
    public void setParam(String param) {
        String[] params = param.split(",");
        value = Double.valueOf(params[0].trim());
        endValue = new Value<>(params[0].trim(), this.path);
        keyword = params.length > 1 ? params[1].trim() : null;
        if (params.length == 2) {
            startValue = new Value<>(keyword, this.path);
        }
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T call(JSONObject root, Object object) {
        Boolean b = startValue.call(root, object) > endValue.execute(root, root);
        return (T) b;
    }

    @Override
    public <T> T call(JSONObject root, JSONObject object) {
        Boolean b = startValue.call(root, object) > endValue.execute(root, root);
        return (T) b;
    }
}
