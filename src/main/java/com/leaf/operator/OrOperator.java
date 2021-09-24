package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.Value;
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
    private List<Value<Boolean>> values = new LinkedList<>();


    @Override
    public void setParam(String param) {
        String[] arrays = param.split("||");
        for (int i = 0; i < arrays.length; i++) {
            String funStr = arrays[i];
            values.add(new Value<>(funStr, this.path));
        }
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T execute(JSONObject root, JSONObject jsonObject) {
        Boolean b = false;
        for (int i = 0; i < values.size(); i++) {
             b = values.get(i).execute(root, jsonObject);
            if (b == true) {
                return (T) b;
            }
        }
        return (T) b;
    }
}
