package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.function.Value;

/**
 * for(contain(ycc))
 * for(larger(5, ))
 * object数组：
 * 单值数组：
 *
 * @created by ycc
 * @since 2021-09-20
 */
public class ForOperator extends Operator {
    private Value value;

    @Override
    public void setParam(String param) {
        value = new Value(param, this.path);
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    /**
     * 处理单个值数据
     *
     * @param root
     * @param object
     * @param <T>
     * @return
     */
    @Override
    public <T> T call(JSONObject root, Object object) {
        return (T) value.call(root, object);
    }

    @Override
    public <T> T call(JSONObject root, JSONObject object) {
        return (T) value.call(root, object);
    }
}
