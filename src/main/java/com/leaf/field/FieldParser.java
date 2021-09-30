package com.leaf.field;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.leaf.function.Function;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @created by ycc
 * @since 2021-09-16
 */
@Slf4j
public class FieldParser {

    public static Map<String, Field> fieldMap = new HashMap<>();

    static {
        addField("com.leaf.field");
    }

    public static void addField(String path) {
        Set<Class<?>> set = ClassUtil.scanPackage(path);
        for (Class<?> c : set) {
            try {
                Field f = (Field) ReflectUtil.newInstance(c);
                fieldMap.put(c.getSimpleName().replaceAll("Field", "").toLowerCase(), f);
            } catch (Exception e) {
                log.error(c.getSimpleName());
            }
        }
    }

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
            try {
                JSONObject fieldObject = jsonObject.getJSONObject(keyword);
                Field f = fieldMap.get(fieldObject.getString("type")).clone();
                Gson gson = new Gson();
                Field field = gson.fromJson(gson.toJson(f), fieldMap.get(fieldObject.getString("type")).getClass());
                field.setFieldName(keyword);
                field.setFieldJSONObject(fieldObject);
                dataTypeMap.put(keyword, field);
            } catch (Exception e) {
                log.error(keyword);
                log.error(e.getMessage(), e);
            }

        }
        return dataTypeMap;
    }

    public static void addField(String fieldName, Field field) {
        fieldMap.put(fieldName, field);
    }

}
