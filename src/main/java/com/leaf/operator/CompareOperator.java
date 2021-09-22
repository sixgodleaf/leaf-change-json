package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.function.Function;
import com.leaf.function.FunctionParser;

/**
 * compare(fuction1, fuction2, type)
 * <p>
 * type : =,
 *
 * @created by ycc
 * @since 2021-09-20
 */
public class CompareOperator extends Operator {
    private Function startFunction;
    private Function endFunction;
    private Double startNumber;
    private Double endNumber;



    @Override
    public void setExpression(String expression) {
        String[] arrays = expression.split(",");
        String start = arrays[0].trim();
        String end = arrays[1].trim();
        String type = arrays[2].trim();
        if (start.contains("(")) {
            startFunction = FunctionParser.getFunction(start, this.path);
        } else {
            startNumber = Double.valueOf(start);
        }
        if (end.contains("(")) {
            endFunction = FunctionParser.getFunction(end, this.path);
        } else {
            endNumber = Double.valueOf(end);
        }
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T execute(JSONObject root, JSONObject jsonObject) {


        return null;
    }
}
