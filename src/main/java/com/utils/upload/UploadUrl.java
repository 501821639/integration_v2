package com.utils.upload;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GSN on 2017/5/8.
 * 文件上传地址
 */
public class UploadUrl {

    private Map<String, String> map = new HashMap<>(2);

    public UploadUrl() {

        //用户头像
        this.map.put("USER_PORTRAIT", "uploadfile/user/portrait/");

        //单位凭证
        this.map.put("COMPANY_CBL", "uploadfile/company/business_license/");

    }

    public String getValue(String key) {
        return this.map.get(key);
    }

}
