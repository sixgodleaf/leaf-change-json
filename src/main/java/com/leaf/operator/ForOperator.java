package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.function.Function;
import com.leaf.function.FunctionParser;

/**
 * for(contain(ycc))
 * for(large(5, ))
 *
 * @created by ycc
 * @since 2021-09-20
 */
public class ForOperator extends Operator {
    private Function function;

    @Override
    public void setExpression(String expression) {
        int start = expression.indexOf("(");
        int end = expression.lastIndexOf(")");
        String param = expression.substring(start + 1, end);
        function = FunctionParser.getFunction(param, this.path);
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
        T t = function.call(root, object);
        return t;
    }

    @Override
    public <T> T call(JSONObject root, JSONObject object) {
        T t = function.call(root, object);
        return t;
    }
}
