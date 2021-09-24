package com.leaf.function;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * 学习jsonPath 了解对json抽取
 * 函数，支持自定义处理字段的方式
 * 对于一些特殊的函数数据
 * {
 *     "name":"",
 *     "param":{}
 * }
 * 参数说明：
 * * name: 函数名称，根据这个名字查找具体的函数处理数据
 * * param：对应不同的函数有不同的参数，具体查看每个函数的配置

 */
@Slf4j
@Data
public abstract class Function implements Cloneable {
    public String path;

    public void setExpression(String expression) {
        int start = expression.indexOf("(");
        int end = expression.lastIndexOf(")");
        String params = expression.substring(start + 1, end).trim();
        setParam(params);
    }
    public abstract void setParam(String param);

    public abstract void setJSONParams(JSONObject jsonObject);

    public void setParams(String expression){
        if (JSONUtil.isJson(expression)) {
            setJSONParams(JSONObject.parseObject(expression));
        } else {
            try {
                setExpression(expression);

            } catch (Exception e) {

            }
        }
    }

    /**
     * 函数处理数据：单个值，数组，json
     * @param root
     * @param value
     * @param <T>
     * @return
     */
    public <T> T execute(JSONObject root, JSONObject value) {
        Object o;
        if (path.startsWith("^")) {
            o = JSONPath.read(root.toJSONString(), path);
        } else {
            o = JSONPath.read(value.toJSONString(), path);
        }
        if (ObjectUtil.isEmpty(o)) {
            return null;
        }
        if (JSONUtil.isJsonArray(o.toString())) {
            return call(root, JSONArray.parseArray(o.toString()));
        } else {
            return execute(root, o);
        }
    }

    /**
     * object 可以是单个值，也可能是json
     * @param root
     * @param object
     * @param <T>
     * @return
     */
    public <T> T execute(JSONObject root, Object object) {
        if (JSONUtil.isJson(object.toString())) {
            return call(root, JSONObject.parseObject(object.toString()));
        } else {
            return call(root, object);
        }
    }

    /**
     * 处理单个值数据
     * @param root
     * @param object
     * @param <T>
     * @return
     */
    public abstract <T> T call(JSONObject root, Object object);

    public abstract <T> T call(JSONObject root, JSONObject object);


    /**
     * 处理数组数据
     *
     * @param root
     * @param jsonArray
     * @param <T>
     * @return
     */
    public <T> T call(JSONObject root, JSONArray jsonArray) {
        List<Object> results = new LinkedList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Object o = jsonArray.get(i);
            Object t = execute(root, o);
            if (ObjectUtil.isNotEmpty(t)) {
                results.add(t);
            }
        }
        return (T) results;
    }

    @Override
    public Function clone() {
        try {
            return (Function) super.clone();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}