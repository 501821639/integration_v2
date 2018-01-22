package com.utils.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by GSN on 2017/4/1.
 * json日期转换
 */
public class JsonDateValueProcessor implements JsonValueProcessor {

    private String datePattern = "yyyy-MM-dd";

    public JsonDateValueProcessor() {
    }

    public JsonDateValueProcessor(String format) {
        this.datePattern = format;
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value) {
        try {
            if ((value instanceof Date)) {
                SimpleDateFormat sdf = new SimpleDateFormat(this.datePattern, Locale.UK);
                return sdf.format((Date) value);
            }
            return value == null ? "" : value.toString();
        } catch (Exception e) {
        }
        return "";
    }

    public String getDatePattern() {
        return this.datePattern;
    }

    public void setDatePattern(String datePaterns) {
        this.datePattern = datePaterns;
    }
}
