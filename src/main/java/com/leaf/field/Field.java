package com.leaf.field;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.leaf.function.Function;
import com.leaf.function.FunctionParser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * {
 * "path": "",
 * "type": "",
 * "function":{
 * "name": "",
 * "params":{}
 * },
 * "default": ""
 * "interFields":{
 * <p>
 * }
 * }
 * <p>
 * 参数说明 ：
 * *  path：路径，默认使用JSONPath抽取规则进行抽取，需要学习和了解JSONPath
 * *  type：数据类型，该字段对应的数据类型
 * *  default: 默认值
 * *  function：处理这个字段对应的自定义函数，如果没有设置，使用JSONPath抽取对应path路径的数据，function的说明具体查看Function模块
 * *  interField：如果数据是个对象，在这里定义对象里面的数据结构，每个字段构成如上说明一致
 * <p>
 * <p>
 * 字段定义和处理，不同类型的数据进行不同处理
 * create by yechengcheng
 *
 * @param <T>
 */
@Slf4j
@Data
public abstract class Field<T> implements Cloneable {
    public String path;
    public String rootPath;
    public String fieldName;
    public transient TYPE type;
    public T defaultValue;
    public Function function; // 用于处理该字段
    public JSONObject fieldJSONObject;
    public Map<String, Field> interFields = new HashMap<>();


    public Field() {


    }

    public void setFieldJSONObject(JSONObject fieldObject) {
        this.path = fieldObject.getString("path");
        String defaultValue = fieldObject.getString("default");
        this.setDefault(defaultValue);
        String functionObject = fieldObject.getString("function");
        this.function = FunctionParser.getFunction(functionObject, path);
    }

    /**
     * 如果有给字段定义了特别的函数，可以用这个函数处理改字段
     * 否则，使用默认的处理方式
     *
     * @return
     */
    public T fieldExecute(JSONObject root, JSONObject current) {
        try {
            T result;
            if (function != null) {
                result = function.execute(root, current);
            } else if (StrUtil.isEmpty(path)) {
                result = getDefault();
            } else {
                if (path.startsWith("^")) {
                    result = getValue(root, path.substring(1, path.length()));
                } else {
                    result = getValue(current, path);
                }
            }
            if (result == null) {
                return getDefault();
            }
            return result;
        } catch (Exception e) {
            log.error("对接日志监控，加个函数处理，比如将日志上传到elasticsearch做监控");
            log.error("fieldname : [{}], function : [{}]", this.getFieldName(), this.getPath(), this.getFieldJSONObject());
            log.error(e.getMessage(), e);
        }
        return null;
    }


    public Field withPath(String path) {
        this.path = path;
        return this;
    }

    public Field withFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public Field withFunction(Function function) {
        this.function = function;
        return this;
    }


    @Override
    public Field clone() {
        try {
            return (Field) super.clone();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 对改字段的处理方式
     *
     * @param value
     * @return
     */
    public abstract T getValue(String value);

    public abstract T getValue(JSONObject value, String path);

    protected abstract T getDefault();

    protected abstract void setDefault(String defaultValue);

    /**
     * 获取该字段的字节数据，写hbase使用
     *
     * @param
     * @return
     */
//    public abstract byte[] getBytes(JSONObject jsonObject);
    public void addField(String key, Field field) {
        interFields.put(key, field);
    }

}