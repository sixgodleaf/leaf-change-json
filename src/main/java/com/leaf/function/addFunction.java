package com.leaf.function;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * add(start, end)
 *
 * @Author: yechengcheng
 * @Date: 2021/9/18 17:21
 */
@Data
public class addFunction extends Function {
    private String startStr = "";
    private String endStr = "";

    @Override
    public void setExpression(String expression) {
        int start = expression.indexOf("(");
        int end = expression.lastIndexOf(")");
        String params = expression.substring(start + 1, end).trim();
        String[] arrays = params.split(",");
        startStr = arrays[0].trim();
        endStr = arrays.length >= 2 ? arrays[1].trim() : "";
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {
        if (jsonObject != null) {
            path = jsonObject.containsKey("path") ? jsonObject.getString("path") : "";
            startStr = jsonObject.containsKey("start") ? jsonObject.getString("start") : "";
            endStr = jsonObject.containsKey("end") ? jsonObject.getString("end") : "";
        }
    }


    @Override
    public <T> T call(JSONObject root, Object object) {
        String result = (startStr + object.toString() + endStr);
        return (T) result;
    }

    /**
     * 当前replace 函数不需要处理json数据
     *
     * @param root
     * @param object
     * @param <T>
     * @return
     */
    @Override
    public <T> T call(JSONObject root, JSONObject object) {
        return null;
    }


}
