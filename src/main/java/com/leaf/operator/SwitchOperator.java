package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.Value;
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
    private Map<Value<Boolean>, Value> valueMap = new HashMap<>();

    @Override
    public void setParam(String param) {
        String[] arrays = param.split(",");
        for (int i = 0; i < arrays.length; i++) {
            String[] funAndValue = arrays[i].split("->");
            Value start = new Value(funAndValue[0], this.path);
            Value end = new Value(funAndValue[1], this.path);
            valueMap.put(start, end);
        }
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {
        System.out.println();
    }

    @Override
    public <T> T execute(JSONObject root, JSONObject jsonObject) {
        for (Value<Boolean> value : valueMap.keySet()) {
            Boolean b = value.execute(root, jsonObject);
            if (b) {
                return (T) valueMap.get(value).execute(root, jsonObject);
            }
        }
        return null;
    }
}
