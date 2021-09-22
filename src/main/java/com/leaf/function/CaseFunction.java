//package com.leaf.function;
//
//import com.alibaba.fastjson.JSONObject;
//
///**
// * @created by ycc
// * @since 2021-09-20
// */
//public class CaseFunction extends Function {
//    private Object value;
//
//
//    @Override
//    public void setExpression(String expression) {
//        int start = expression.indexOf("(");
//        int end = expression.lastIndexOf(")");
//        String name = expression.substring(0, start);
//        String params = expression.substring(start + 1, end).trim();
//        this.value = params;
//    }
//
//    @Override
//    public void setJSONParams(JSONObject jsonObject) {
//
//    }
//
//    @Override
//    public void setParams(String expression) {
//
//    }
//
//    @Override
//    public <T> T execute(JSONObject root, JSONObject jsonObject) {
////        JSONPath.read()
//        return null;
//    }
//
//    public <B> B getValue() {
//        return (B) value;
//    }
//}
