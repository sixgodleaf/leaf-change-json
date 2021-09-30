package com.leaf.function;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @created by ycc
 * @since 2021-09-16
 */
@Slf4j
public class FunctionParser {

    public static Map<String, Function> functionMap = new HashMap<>();

    static {
        addFunction("com.leaf.function");
        addFunction("com.leaf.operator");
    }

    public static void addFunction(String path) {
        Set<Class<?>> set = ClassUtil.scanPackage(path);
        for (Class<?> c : set) {
            try {
                Function f= (Function) ReflectUtil.newInstance(c);
                functionMap.put(c.getSimpleName().replaceAll("Function|Operator", "").toLowerCase(), f);
            } catch (Exception e) {
                log.error(c.getSimpleName());
            }
        }
    }

    /**
     * 解析文件，将文件解析成对应索引
     *
     * @return
     */

    public static Function getFunction(String funStr, String path) {
        if (StrUtil.isEmpty(funStr)) {
            return null;
        }
        String name;
        if (JSONUtil.isJson(funStr)) {
            JSONObject jsonObject = JSONObject.parseObject(funStr);
            name = jsonObject.getString("name");
        } else {
            int start = funStr.indexOf("(");
            name = funStr.substring(0, start);
        }
        Function funstr = functionMap.get(name).clone();
        Gson gson = new Gson();
        Function function = gson.fromJson(gson.toJson(funstr), functionMap.get(name).getClass());
        try {
            function.setPath(path);
            function.setParams(funStr);
        } catch (Exception e) {
            log.error("path: {}, expression: {}", path, funStr);
            log.error(e.getMessage(), e);
            System.exit(0);
        }

        return function;
    }

    public static void addFunction(String name, Function function) {
        functionMap.put(name, function);
    }

}
