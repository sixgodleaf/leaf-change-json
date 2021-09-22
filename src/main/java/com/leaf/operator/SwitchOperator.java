package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.function.Function;
import com.leaf.function.FunctionParser;

import java.util.HashMap;
import java.util.Map;

/**
 * switch(contain(fb#RHINO#)->3, contain(link)->6)
 *
 * @created by ycc
 * @since 2021-09-20
 */
public class SwitchOperator extends Operator {

    private Map<Function, Object> objectMap = new HashMap<>();

    @Override

    public void setExpression(String expression) {
        int start = expression.indexOf("(");
        int end = expression.lastIndexOf(")");
        String params = expression.substring(start + 1, end).trim();
        String[] arrays = params.split(",");
        for (int i = 0; i < arrays.length; i++) {
            String[] funAndValue = arrays[i].split("->");
            Function function = FunctionParser.getFunction(funAndValue[0], this.path);
            objectMap.put(function, funAndValue[1]);
        }
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {
        System.out.println();
    }

    @Override
    public <T> T execute(JSONObject root, JSONObject jsonObject) {
        for (Function fu : objectMap.keySet()) {
            Boolean b = fu.execute(root, jsonObject);
            if (b) {
                return (T) objectMap.get(fu);
            }
        }
        return null;
    }
}
