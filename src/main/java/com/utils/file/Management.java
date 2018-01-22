package com.utils.file;

import java.io.File;

/**
 * Created by GSN on 2017/5/26.
 * 文件增删改操作
 */
public class Management {


    /**
     * 删除指定的文件
     *
     * @param url 地址
     * @return true：删除成功
     */
    public Boolean deleteFile(String url) {

        File file = new File(url);

        boolean b = file.delete();
        if (!b) {
            b = file.delete();
        }
        return b;
    }

}
