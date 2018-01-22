package com.utils.upload;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;

/**
 * Created by GSN on 2017/4/1.
 * spring mvc 上传
 */
public class SpringMvcUpload {


    private CommonsMultipartFile commonsMultipartFile = null;


    public SpringMvcUpload(CommonsMultipartFile commonsMultipartFile) {
        this.commonsMultipartFile = commonsMultipartFile;
    }

    /**
     * 不进行文件名处理
     *
     * @param path 上传路径
     * @throws Exception 上传失败
     */
    public void run(String path) throws Exception {
        extractCode(path, commonsMultipartFile.getOriginalFilename());
    }


    /**
     * 文件名处理：用户id - 时间毫秒
     *
     * @param path   上传路径
     * @param userId 用户id id=0 只生成时间毫秒值
     * @return 文件名称
     * @throws Exception 上传失败
     */
    public String run(String path, int userId) throws Exception {

        // 获取文件名进行分割
        String[] commonsMultipartFileName = commonsMultipartFile.getOriginalFilename().split("\\.");

        // 生成文件名
        String fileName;

        if (userId == 0) {
            fileName = new Date().getTime() + "." + commonsMultipartFileName[(commonsMultipartFileName.length - 1)];
        } else {
            fileName = userId + "-" + new Date().getTime() + "." + commonsMultipartFileName[(commonsMultipartFileName.length - 1)];
        }

        extractCode(path, fileName);

        return fileName;

    }

    /**
     * @param path     上传路径
     * @param fileName 文件名称
     * @throws Exception 异常
     */
    private void extractCode(String path, String fileName) throws Exception {

        if (commonsMultipartFile == null) {
            throw new CommonsMultipartFileExption(" CommonsMultipartFile实例化失败：不能为null");
        }

        File f = new File(path);

        if (!f.exists()) {
            f.mkdirs(); // 不存在则创建文件夹
        }

        commonsMultipartFile.getFileItem().write(new File(path + fileName));

    }


}
