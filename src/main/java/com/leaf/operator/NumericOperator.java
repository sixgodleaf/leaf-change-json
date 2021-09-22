package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.function.Function;
import com.leaf.function.FunctionParser;

/**
 * numeric()
 * @created by ycc
 * @since 2021-09-20
 */
public class NumericOperator<T> extends Operator {
    private String type;
    private Function startFunction;
    private Function endFunction;
    private Double startNumber;
    private Double endNumber;

    @Override
    public void setExpression(String expression) {
        String[] arrays = expression.split(",");
        String start =arrays[0].trim();
        if (start.contains("(")) {
            startFunction = FunctionParser.getFunction(start, this.path);
        } else {
            startNumber = Double.valueOf(start);
        }
        String end =arrays[1].trim();
        if (end.contains("(")) {
            endFunction = FunctionParser.getFunction(end, this.path);
        } else {
            endNumber = Double.valueOf(end);
        }
        type =arrays[2].trim();
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T execute(JSONObject root, JSONObject jsonObject) {
        if (startFunction != null) {
            startNumber = startFunction.execute(root, jsonObject);
        }

        if (endFunction != null) {
            endNumber = endFunction.execute(root, jsonObject);
        }

        Double result =  null;
        if ("+".equalsIgnoreCase(type)) {
            result = startNumber + endNumber;
        } else if ("-".equalsIgnoreCase(type)) {
            result = startNumber - endNumber;
        } else if ("*".equalsIgnoreCase(type)) {
            result = startNumber * endNumber;
        }else if ("/".equalsIgnoreCase(type)) {
            result = startNumber / endNumber;
        }

        return (T) result;
    }
}
