//package com.leaf.function;
//
//import cn.hutool.json.JSONUtil;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.JSONPath;
//
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * {"function":{
// * "path":"$.data[*].actor",
// * "keyword":"repost_status|status_type|channel",
// * "pathKey","actor",
// * "name":"Json2ArrayFunction"
// * }}
// * 处理多个数据
// * @Author: yechengcheng
// * @Date: 2021/9/17 17:29
// */
//
//public class Json2ArrayFunction extends Function {
//    private String path;
//    private String pathKey;
//    private String[] keywords;
//
//    public Json2ArrayFunction(JSONObject jsonObject) {
//
//    }
//
//    @Override
//    public List<JSONObject> execute(JSONObject root, JSONObject jsonObject) {
//        List<JSONObject> jsonObjectList = new LinkedList<>();
//        JSONObject newObject = new JSONObject();
//        for (String k : keywords) {
//            newObject.put(k, jsonObject.get(k));
//        }
//        JSONArray jsonArray = (JSONArray) JSONPath.read(jsonObject.toJSONString(), path);
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//            JSONObject newJSON = (JSONObject) newObject.clone();
//            newJSON.put(pathKey, jsonObject1);
//            jsonObjectList.add(newJSON);
//        }
//        return jsonObjectList;
//    }
//
//    @Override
//    public void setExpression(String expression) {
//
//    }
//
//    @Override
//    public void setJSONParams(JSONObject jsonObject) {
//        path = jsonObject.getString("path");
//        String keyword = jsonObject.getString("keyword");
//        pathKey = jsonObject.getString("pathKey");
//        keywords = keyword.split("\\|");
//    }
//
//
//
//}
