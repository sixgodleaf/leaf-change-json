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
public class DateField extends Field<Date> {
    protected TYPE type = TYPE.DATE;
    private String format;
    public ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal;

    public DateField() {

    }
    public DateField(JSONObject fieldObject) {
        super(fieldObject);
        this.format = fieldObject.getString("format");
        simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> {
            SimpleDateFormat ret = new SimpleDateFormat(format);
            ret.setTimeZone(TimeZone.getTimeZone("UTC"));
            return ret;
        });
    }


    @Override
    public Date getValue(String value) {
        return null;
    }

    @Override
    public Date getValue(JSONObject value) {
        String dateStr = (String) JSONPath.read(value.toJSONString(), path);
        try {
            return simpleDateFormatThreadLocal.get().parse(dateStr);
        } catch (ParseException e) {
            return getValue(dateStr);
        }
    }

//    @Override
//    public Date getValue(JSONObject value) {
//        return (Date) JSONPath.read(value.toJSONString(), path);
//    }

    @Override
    protected Date getDefault() {
        return null;
    }

    @Override
    protected void setDefault(String defaultValue) {

    }
}
