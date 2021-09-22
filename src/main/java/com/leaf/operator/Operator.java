package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.function.Function;

/**
 * @created by ycc
 * @since 2021-09-20
 */
public abstract class Operator extends Function {
    @Override
    public <T> T call(JSONObject root, Object object) {
        return null;
    }

    @Override
    public <T> T call(JSONObject root, JSONObject object) {
        return null;
    }


}
