package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.function.Function;
import com.leaf.function.FunctionParser;

import java.util.LinkedList;
import java.util.List;

/**
 * pipeline(replace(new,old)|add(start, end))
 * @created by ycc
 * @since 2021-09-20
 */
public class PipelineOperator extends Operator {
  private List<Function> functions = new LinkedList<>();
    @Override
    public void setExpression(String expression) {
        int start = expression.indexOf("(");
        int end = expression.lastIndexOf(")");
        String params = expression.substring(start + 1, end).trim();
        String[] arrays = params.split("\\|");
        for (int i = 0; i < arrays.length; i++) {
          functions.add(FunctionParser.getFunction(arrays[i], this.path));
        }
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T execute(JSONObject root, Object object) {
        for (int i = 0; i < functions.size(); i++) {
            Function function = functions.get(i);
            object = function.execute(root, object);
        }
        return (T) object;
    }
}
