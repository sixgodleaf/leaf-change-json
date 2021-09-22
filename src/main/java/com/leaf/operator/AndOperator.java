package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.function.Function;
import com.leaf.function.FunctionParser;

import java.util.LinkedList;
import java.util.List;

/**
 * {
 *     "name": "and",
 *     "function":for(or(and(isnumber(.b)&&isdouble(.d))))
 * }
 * and(contain(fb#RHINO#),contain(link))
 * @created by ycc
 * @since 2021-09-20
 */
public class AndOperator extends Operator {

    private List<Function> functions = new LinkedList<>();

    @Override
    public void setExpression(String expression) {
        String[] arrays = expression.split(",");
        for (int i = 0; i < arrays.length; i++) {
            String funStr = arrays[i];
            Function function = FunctionParser.getFunction(funStr, this.path);
            functions.add(function);
        }
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T execute(JSONObject root,JSONObject jsonObject) {
        Boolean b = true;
        for (int i = 0; i < functions.size(); i++) {
             b = functions.get(i).execute(root, jsonObject);
            if (b == false) {
                break;
            }
        }
        return (T) b;
    }

    public static void main(String[] args) {
        String funStr = "and(isnumber(.b)&&isdouble(.d))";
        int start = funStr.indexOf("(");
        int end = funStr.lastIndexOf(")");
        String name = funStr.substring(0, start);
        String param = funStr.substring(start+1, end);
        System.out.println(param + name);
    }
}
