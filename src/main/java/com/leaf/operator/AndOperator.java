package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.Value;
import com.leaf.function.Function;
import com.leaf.function.FunctionParser;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.LinkedList;
import java.util.List;

/**

 * and(contain(fb#RHINO#),contain(link))
 * @created by ycc
 * @since 2021-09-20
 */
public class AndOperator extends Operator {

    private List<Value> values = new LinkedList<>();

    @Override
    public void setParam(String expression) {
        String[] arrays = expression.split(",");
        for (int i = 0; i < arrays.length; i++) {
            String funStr = arrays[i];
            Value value = new Value(funStr, this.path);
            values.add(value);
        }
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T execute(JSONObject root,JSONObject jsonObject) {
        Boolean b = true;
        for (int i = 0; i < values.size(); i++) {
             b = values.get(i).execute(root, jsonObject);
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
