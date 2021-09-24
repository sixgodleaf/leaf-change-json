package com.leaf.function;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leaf.Value;

/**
 * contain(fb#RHINO#)
 * switch(contain(fb#RHINO#)->3|contain)
 *
 * @created by ycc
 * @since 2021-09-20
 */
public class ContainFunction extends Function {
//    private String value;
    private Value<String> value;

    @Override
    public void setParam(String param) {
        this.value = new Value<>(param, this.path);

    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T call(JSONObject root, Object object) {
        Boolean b = object.toString().contains(value.execute(root, root));
        return (T) b;
    }

    @Override
    public <T> T call(JSONObject root, JSONObject object) {
        Boolean b = object.keySet().contains(value.execute(root, root));
        return (T) b;
    }

    /**
     * 处理数组contain
     *
     * @param root
     * @param jsonArray
     * @param <T>
     * @return
     */
    @Override
    public <T> T call(JSONObject root, JSONArray jsonArray) {
        Boolean b = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            String jValue = jsonArray.getString(i);
            if (jValue.equals(value.execute(root, root))) {
                b = true;
                break;
            }
        }
        return (T) b;
    }
}
