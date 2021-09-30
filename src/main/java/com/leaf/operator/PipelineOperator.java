package com.leaf.operator;

import com.alibaba.fastjson.JSONObject;
import com.leaf.function.Value;

import java.util.LinkedList;
import java.util.List;

/**
 * pipeline(replace(new,old)|add(start, end))
 * @created by ycc
 * @since 2021-09-20
 */
public class PipelineOperator extends Operator {
  private List<Value> values = new LinkedList<>();
    @Override
    public void setParam(String param) {
        String[] arrays = param.split("\\|");
        for (int i = 0; i < arrays.length; i++) {
          values.add(new Value(arrays[i], this.path));
        }
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T execute(JSONObject root, Object object) {
        for (int i = 0; i < values.size(); i++) {
            Value value = values.get(i);
            object = value.execute(root, object);
        }
        return (T) object;
    }
}
