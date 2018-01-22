package com.utils.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import java.util.Date;
import java.util.List;

/**
 * Created by GSN on 2017/4/1.
 * java转json
 */
public class FormatJson {

    //自定义日期格式 为null不进行日期处理
    private String strDate = null;

    //true：处理hibernate一对一关联映射
    private boolean dependenceMapping = false;

    public FormatJson() {

    }

    public FormatJson(String strDate) {
        this.strDate = strDate;
    }

    public FormatJson(boolean dependenceMapping) {
        this.dependenceMapping = dependenceMapping;
    }

    public FormatJson(String strDate, boolean dependenceMapping) {
        this.strDate = strDate;
        this.dependenceMapping = dependenceMapping;
    }


    /**
     * list格式化json
     *
     * @param list
     * @return String
     */
    public String listConversion(List<?> list) {
        JsonConfig jsonConfig = new JsonConfig();
        if (this.dependenceMapping) {
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        }
        if (this.strDate != null && !this.strDate.trim().equals("")) {
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(this.strDate));
        }
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
        return jsonArray.toString();
    }


    /**
     * Object格式化json
     *
     * @param obj
     * @return String
     */
    public String objectConversion(Object obj) {
        JsonConfig jsonConfig = new JsonConfig();
        if (this.dependenceMapping) {
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        }
        if (this.strDate != null && !this.strDate.trim().equals("")) {
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(this.strDate));
        }
        return JSONObject.fromObject(obj, jsonConfig).toString();
    }

}
