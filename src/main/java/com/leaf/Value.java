package com.leaf;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.leaf.function.Function;
import com.leaf.function.FunctionParser;

import java.util.List;

/**
 * root， current
 * 参数使用这个类封装
 * 每个参数都可能通过这些方式获取
 * 1. path：通过路径获取这个数据
 * 2. value：直接传进去的数值
 * 3. function：通过函数获取
 * 根据条件获取具体的数据返回
 * <p>
 * 对于value的使用，需要搞清楚一点，什么函数是针对当前数据，什么函数通过其他方式获取其他数据处理
 *
 * @created by ycc
 * @since 2021-09-23
 */
public class Value<T> extends Function {
    private String rootPath;
    private String newPath;
    private T value;
    private Function function;

    public Value(String param, String rootPath) {
        if (param.contains("(")) {
            int start = param.indexOf("(");
            String functionName = param.substring(0, start);
            if (FunctionParser.functionMap.containsKey(functionName)) {
                function = FunctionParser.getFunction(param, rootPath);
            } else {
                value = (T) param;
            }
        } else {
            value = (T) param;
        }
    }

    @Override
    public T execute(JSONObject root, JSONObject current) {
        if (function != null) {
            return function.execute(root, current);
        } else if (value != null) {
            return value;
        } else if (StrUtil.isNotEmpty(newPath)) {
            return (T) JSONPath.read(current.toJSONString(), newPath);
        } else {
            return null;
        }
    }

    @Override
    public void setParam(String param) {

    }

    @Override
    public void setJSONParams(JSONObject jsonObject) {

    }

    @Override
    public T call(JSONObject root, Object object) {
        return function.call(root, object);
    }

    @Override
    public T call(JSONObject root, JSONObject object) {
        return function.call(root, object);
    }
}
