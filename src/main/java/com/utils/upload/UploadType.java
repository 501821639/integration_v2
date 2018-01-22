package com.utils.upload;

import java.util.*;

/**
 * Created by GSN on 2017/4/1.
 * <p>
 * 上传文件所支持的扩展名
 */
public class UploadType {

    private Map<String, List<String>> map = new HashMap<>(5);

    public UploadType() {

        // 图片
        List<String> list = new ArrayList<>();
        list.add("jpg");
        list.add("jpeg");
        list.add("png");
        list.add("gif");
        this.map.put("image", list);

        // 视频
        List<String> list1 = new ArrayList<>();
        list1.add("mp4");
        list1.add("3gp");
        this.map.put("video", list1);

        // 音频
        List<String> list2 = new ArrayList<>();
        list2.add("mp3");
        this.map.put("audio", list2);

        // 压缩包
        List<String> list3 = new ArrayList<>();
        list3.add("zip");
        list3.add("rar");
        this.map.put("cp", list3);

        // office
        List<String> list4 = new ArrayList<>();
        list4.add("xlsx");
        list4.add("xls");
        list4.add("ppt");
        list4.add("pptx");
        list4.add("doc");
        list4.add("docx");
        this.map.put("office", list4);

    }

    /**
     * 验证文件扩展名是否符合要求
     *
     * @param fileType image:图片, video:视频, audio:音频, cp:压缩包,office:word excel, all : 所有
     * @param fileName 要验证的文件
     * @return true:文件符合要求
     */
    public Boolean verification(String fileType, String fileName) {

        // 文件扩展名
        String kzm;

        if (fileName.split("\\.").length < 2) {
            kzm = fileName;
        } else {
            String[] arr = fileName.split("\\.");
            kzm = arr[arr.length - 1];
        }

        if ("all".equalsIgnoreCase(fileType)) {

            Set<String> set = this.map.keySet();

            for (String allFileType : set) {

                List<String> l = this.map.get(allFileType);

                for (String type : l) {

                    if (type.equalsIgnoreCase(kzm)) {
                        return true;
                    }

                }

            }

        } else {

            List<String> l = this.map.get(fileType);

            for (String type : l) {

                if (type.equalsIgnoreCase(kzm)) {
                    return true;
                }

            }

        }

        return false;
    }


}
