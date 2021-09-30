package com.leaf.field;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期处理方式
 * @author ycc
 */
@Slf4j
public class DateField extends Field<Long> {
    protected TYPE type = TYPE.DATE;
    private String startFormat;
    private String endFormat;
    public ThreadLocal<SimpleDateFormat> startDateFormatThreadLocal;
    public ThreadLocal<SimpleDateFormat> endDateFormatThreadLocal;

    public DateField() {

    }

    @Override
    public void setFieldJSONObject(JSONObject fieldObject) {
        super.setFieldJSONObject(fieldObject);
        this.startFormat = fieldObject.getString("from_format");
        this.endFormat = fieldObject.getString("to_formart");
        startDateFormatThreadLocal = ThreadLocal.withInitial(() -> {
            SimpleDateFormat ret = new SimpleDateFormat(startFormat);
            ret.setTimeZone(TimeZone.getTimeZone("UTC"));
            return ret;
        });

        endDateFormatThreadLocal = ThreadLocal.withInitial(() -> {
            SimpleDateFormat ret = new SimpleDateFormat(startFormat);
            ret.setTimeZone(TimeZone.getTimeZone("UTC"));
            return ret;
        });
    }

    @Override
    public Long getValue(Object value) {
        return null;
    }

    public void setParam() {

    }


    public Long getValue(String value) {
        return Long.valueOf(value);
    }

    @Override
    public Long getValue(JSONObject value, String path) {
        String dateStr = (String) JSONPath.read(value.toJSONString(), path);
        try {
            Date date = startDateFormatThreadLocal.get().parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            return getValue(dateStr);
        }
    }

//    @Override
//    public Date getValue(JSONObject value) {
//        return (Date) JSONPath.read(value.toJSONString(), path);
//    }

    @Override
    protected Long getDefault() {
        return null;
    }

    @Override
    protected void setDefault(String defaultValue) {

    }
}
