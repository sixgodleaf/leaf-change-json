package com.leaf.function;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.leaf.operator.SwitchOperator;
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
        Set<Class<?>> set = ClassUtil.scanPackage("com.leaf.function");
        Set<Class<?>> set1 = ClassUtil.scanPackage("com.leaf.operator");
        for (Class<?> c : set) {
            try {
               Function f= (Function) ReflectUtil.newInstance(c);
                functionMap.put(c.getSimpleName().replaceAll("Function|Operator", "").toLowerCase(), f);
            } catch (Exception e) {
                log.error(c.getSimpleName());
            }
        }
        for (Class<?> c : set1) {
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

//    public static Function getFunction(String funStr, String path) {
//        if (StrUtil.isEmpty(funStr)) {
//            return null;
//        }
//        String name;
//        if (JSONUtil.isJson(funStr)) {
//            JSONObject jsonObject = JSONObject.parseObject(funStr);
//            name = jsonObject.getString("name");
//        } else {
//            int start = funStr.indexOf("(");
//            name = funStr.substring(0, start);
//        }
//        Function function = null;
//        if (name.equalsIgnoreCase("replace")) {
//            function = new ReplaceFunction();
//        } else if (name.equalsIgnoreCase("switch")) {
//            function = new SwitchOperator();
//        } else if (name.equalsIgnoreCase("contain")) {
//            function = new ContainFunction();
//        }
//        function.setPath(path);
//        function.setParams(funStr);
//        return function;
//    }

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
        Function function = functionMap.get(name).clone();
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


//    public static Function getFunction(JSONObject jsonObject) {
//        if (jsonObject == null) {
//            return null;
//        }
//        String type = jsonObject.getString("name");
//        if (type.equalsIgnoreCase("ReplaceFunction")) {
//            return new ReplaceFunction(jsonObject);
//        } else if (type.equalsIgnoreCase("DefaultIdFunction")) {
//            return null;
//        }
//        return null;
//    }
//    public static Function getFunction(String functionName, String param) {
//
//        if (functionName.equalsIgnoreCase("ReplaceFunction")) {
//            return new ReplaceFunction(param);
//        } else if (functionName.equalsIgnoreCase("DefaultIdFunction")) {
//            return null;
//        }
//        return null;
//    }


    public static void main(String[] args) {
        Set<Class<?>> set = ClassUtil.scanPackage("com.leaf.function");
        for (Class<?> c : set) {
            System.out.println(c.getName());
        }
    }
}
