package com.leaf.function;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * contain(fb#RHINO#)
 * switch(contain(fb#RHINO#)->3|contain)
 * @created by ycc
 * @since 2021-09-20
 */
public class ContainFunction extends Function {
    private String value;

    @Override
    public void setExpression(String expression) {
        int start = expression.indexOf("(");
        int end = expression.lastIndexOf(")");
        String name = expression.substring(0, start);
        value = expression.substring(start + 1, end).trim();
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public <T> T execute(JSONObject root, JSONObject jsonObject) {
        Boolean b = false;
        Object str =  JSONPath.read(jsonObject.toJSONString(), path);
        if (JSONUtil.isJsonArray(str.toString())) {
            JSONArray jsonArray = (JSONArray) str;
            for (int i = 0; i < jsonArray.size(); i++) {
                String jValue =  jsonArray.getString(i);
                if (jValue.equals(value)) {
                    b = true;
                    break;
                }
            }
        } else {
            if (str.toString().contains(value)) {
                b = true;
            }
        }
        return (T) b;
    }



    @Override
    public <T> T call(JSONObject root, Object object) {
        Boolean b = object.toString().contains(value);
        return (T) b;
    }

    @Override
    public <T> T call(JSONObject root, JSONObject object) {
        Boolean b= object.keySet().contains(value);
        return (T) b;
    }

    /**
     * 处理数组contain
     * @param root
     * @param jsonArray
     * @param <T>
     * @return
     */
    @Override
    public <T> T call(JSONObject root, JSONArray jsonArray) {
        Boolean b = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            String jValue =  jsonArray.getString(i);
            if (jValue.equals(value)) {
                b = true;
                break;
            }
        }
        return (T) b;
    }
}
