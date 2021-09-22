//package com.leaf.function;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
///**
// * @created by ycc
// * @since 2021-09-20
// */
//public class SumFunction extends Function {
//    private String path;
//
//    @Override
//    public void setExpression(String expression) {
//        String[] arrays = expression.split(",");
//        this.path = arrays[0];
//    }
//
//    @Override
//    public void setJSONParams(JSONObject jsonObject) {
//
//    }
//
//    @Override
//    public <T> T execute(JSONObject root, JSONObject value) {
//        JSONArray jsonArray = value.getJSONArray(path);
//        Double sum=0.0;
//        for (int i = 0; i < jsonArray.size(); i++) {
//            sum = sum + (Double) jsonArray.get(i);
//        }
//        return (T) sum;
//    }
//}
