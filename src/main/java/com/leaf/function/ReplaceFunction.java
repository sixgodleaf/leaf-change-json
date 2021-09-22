package com.leaf.function;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * replace(new, old)
 *
 * @Author: yechengcheng
 * @Date: 2021/9/18 17:21
 */
@Data
public class ReplaceFunction extends Function {
    private String oldStr;
    private String newStr;

    @Override
    public void setExpression(String expression) {
        int start = expression.indexOf("(");
        int end = expression.lastIndexOf(")");
        String params = expression.substring(start + 1, end).trim();
        String[] arrays = params.split(",");
        newStr = arrays[0].trim();
        oldStr = arrays.length >= 2 ? arrays[1].trim() : "";
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {
        if (jsonObject != null) {
            path = jsonObject.containsKey("path") ? jsonObject.getString("path") : "";
            newStr = jsonObject.containsKey("new") ? jsonObject.getString("new") : "";
            oldStr = jsonObject.containsKey("old") ? jsonObject.getString("old") : "";
        }
    }


    @Override
    public <T> T call(JSONObject root, Object object) {
        String result = object.toString().replaceAll(oldStr, newStr);
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
