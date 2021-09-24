package com.leaf.function;

import com.alibaba.fastjson.JSONObject;
import com.leaf.Value;
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
    private Value<String> oldValue;
    private Value<String> newValue;

    @Override
    public void setParam(String param) {
        String[] arrays = param.split(",");
        newStr = arrays[0].trim();
        newValue = new Value(newStr, this.path);
        oldStr = arrays.length >= 2 ? arrays[1].trim() : "";
        oldValue = new Value(oldStr, this.path);
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
        String result = object.toString().replaceAll(oldValue.execute(root, root), newValue.execute(root, root));
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
