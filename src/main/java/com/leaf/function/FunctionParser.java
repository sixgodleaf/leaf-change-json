package com.leaf.function;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.leaf.operator.SwitchOperator;

/**
 * @created by ycc
 * @since 2021-09-16
 */
public class FunctionParser {


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
        Function function = null;
        if (name.equalsIgnoreCase("replace")) {
            function = new ReplaceFunction();
        } else if (name.equalsIgnoreCase("switch")) {
            function = new SwitchOperator();
        } else if (name.equalsIgnoreCase("contain")) {
            function = new ContainFunction();
        }
        function.setPath(path);
        function.setParams(funStr);
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

}
