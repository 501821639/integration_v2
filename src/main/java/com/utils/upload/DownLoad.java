package com.utils.upload;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by GSN on 2017/4/1.
 * 下载
 */
public class DownLoad {

    private HttpServletResponse response = null;
    private InputStream inputStream = null;
    private OutputStream os = null;


    public DownLoad(HttpServletResponse response) {
        this.response = response;
    }


    /**
     * 下载
     *
     * @param pathName 路径+文件名称
     */
    public void run(String pathName) {
        extractCode(pathName);
    }

    /**
     * 下载
     *
     * @param path     路径
     * @param fileName 文件名称
     */
    public void run(String path, String fileName) {
        extractCode(path + fileName);
    }


    /**
     * 开始下载
     *
     * @param pathName 路径+文件名称
     */
    private void extractCode(String pathName) {

        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + pathName);

        File file = new File(pathName);

        try {

            inputStream = new FileInputStream(file);
            os = response.getOutputStream();

            byte[] b = new byte[1024];
            int length;

            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
