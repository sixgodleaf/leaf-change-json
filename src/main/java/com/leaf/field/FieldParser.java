package com.leaf.field;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @created by ycc
 * @since 2021-09-16
 */
public class FieldParser {


    /**
     * 解析文件，将文件解析成对应索引
     *
     * @param jsonObject
     * @return
     */
    public static Map<String, Field> parse(JSONObject jsonObject) {
        Map<String, Field> dataTypeMap = new HashMap<>();
        Set<String> keySet = jsonObject.keySet();
        for (String keyword : keySet) {
            JSONObject fieldObject = jsonObject.getJSONObject(keyword);
            Field field = getField(keyword, fieldObject);
            dataTypeMap.put(keyword, field);
        }
        return dataTypeMap;
    }

    private static Field getField(String fieldName, JSONObject fieldObject) {
        String type = fieldObject.getString("type");
        Field field = null;
        if (type.equalsIgnoreCase("int")) {
            field = new IntegerField(fieldObject);
        } else if (type.equalsIgnoreCase("array")) {
            field = new ArrayField(fieldObject);
        } else if (type.equalsIgnoreCase("arrayobject")) {
            field = new ArrayObjectField(fieldObject);
        } else if (type.equalsIgnoreCase("boolean")) {
            field = new BooleanField(fieldObject);
        } else if (type.equalsIgnoreCase("date")) {
            field = new DateField(fieldObject);
        } else if (type.equalsIgnoreCase("double")) {
            field = new DoubleField(fieldObject);
        } else if (type.equalsIgnoreCase("float")) {
            field = new FloatField(fieldObject);
        } else if (type.equalsIgnoreCase("short")) {
            field = new ShortField(fieldObject);
        } else if (type.equalsIgnoreCase("long")) {
            field = new LongField(fieldObject);
        } else if (type.equalsIgnoreCase("object")) {
            field = new ObjectField(fieldObject);
        } else if (type.equalsIgnoreCase("string")) {
            field = new StringField(fieldObject);
        } else if (type.equalsIgnoreCase("map")) {
            field = new MapField(fieldObject);
        }
        field.setFieldName(fieldName);
        field.setFieldJSONObject(fieldObject);
        return field;
    }

}
