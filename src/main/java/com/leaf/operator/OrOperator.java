package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.function.Function;
import com.leaf.function.FunctionParser;

import java.util.LinkedList;
import java.util.List;

/**
 * or()
 * @created by ycc
 * @since 2021-09-20
 */
public class OrOperator extends Operator {
    private List<Function> functions = new LinkedList<>();


    @Override
    public void setExpression(String expression) {
        String[] arrays = expression.split("||");
        for (int i = 0; i < arrays.length; i++) {
            String funStr = arrays[i];
            functions.add(FunctionParser.getFunction(funStr, this.path));
        }
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T execute(JSONObject root, JSONObject jsonObject) {
        Boolean b = false;
        for (int i = 0; i < functions.size(); i++) {
             b = functions.get(i).execute(root, jsonObject);
            if (b == true) {
                return (T) b;
            }

        }
        return (T) b;
    }
}
