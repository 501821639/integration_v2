package com.utils.file;

import java.io.File;

/**
 * Created by GSN on 2017/5/10.
 * 读取文件夹
 */
public class Read {

    /**
     * 获取该目录下的所有文件夹以及其他内容
     *
     * @param url 要获取的目录
     * @return String数组
     */
    public String[] getFileAll(String url) {

        return new File(url).list();

    }

}
