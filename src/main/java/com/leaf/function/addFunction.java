package com.leaf.function;

import com.alibaba.fastjson.JSONObject;
import com.leaf.Value;
import lombok.Data;

/**
 * add(start, end)
 *
 * @Author: yechengcheng
 * @Date: 2021/9/18 17:21
 */
@Data
public class addFunction extends Function {
    private Value<String> startValue;
    private Value<String> endValue;

    @Override
    public void setParam(String param) {
        String[] arrays = param.split(",");
        String startStr = arrays[0].trim();
        startValue = new Value(startStr, this.path);
        String endStr = arrays.length >= 2 ? arrays[1].trim() : "";
        endValue = new Value(endStr, this.path);
    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {
        if (jsonObject != null) {
            path = jsonObject.containsKey("path") ? jsonObject.getString("path") : "";
            //String startStr = jsonObject.containsKey("start") ? jsonObject.getString("start") : "";
//            String endStr = jsonObject.containsKey("end") ? jsonObject.getString("end") : "";
        }
    }


    @Override
    public <T> T call(JSONObject root, Object object) {
        String result = (startValue.execute(root, root) + object.toString() + endValue.execute(root, root));
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
