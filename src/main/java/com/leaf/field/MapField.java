package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * 返回多个字段，比如算法处理，根据内容调算法接口，然后处理结果有多个字段，返回算法的多个字段。
 * @created by ycc
 * @since 2021-09-18
 */
public class MapField extends Field<Map<String,Object>> {
    private List<String> keyList = new ArrayList<>();

    private Class aClass = Map.class;

    public MapField() {

    }


    @Override
    public Map<String, Object> getValue(Object value) {
        return getValue(value.toString());
    }


    public Map<String, Object> getValue(String value) {
        return new Gson().fromJson(value, Map.class);
    }

    @Override
    public Map<String, Object> getValue(JSONObject value, String path) {

        return null;
    }

    @Override
    protected Map<String, Object> getDefault() {
        return null;
    }

    @Override
    protected void setDefault(String defaultValue) {

    }

}
