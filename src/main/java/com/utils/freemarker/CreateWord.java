package com.utils.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

/**
 * Created by GSN on 2017/12/5.
 * 创建word文档
 *
 * @author 郭少男
 */
public class CreateWord {

    private Map dataMap;

    public CreateWord(Map dataMap) {
        this.dataMap = dataMap;
    }

    /**
     * 生成word
     *
     * @param inputPath  模板路径
     * @param outputPath 输出位置
     * @param name       模板名称
     */
    public void start(String inputPath, String outputPath, String name) {

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        Writer out = null;

        try {


            //创建配置实例
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

            //设置编码
            configuration.setDefaultEncoding("UTF-8");

            //ftl模板文件统一路径
            configuration.setDirectoryForTemplateLoading(new File(inputPath));

            //获取模板
            Template template = configuration.getTemplate(name);


            //输出文件
            File outFile = new File(outputPath + System.currentTimeMillis() + ".doc");

            //将模板和数据模型合并生成文件
            fos = new FileOutputStream(outFile);
            osw = new OutputStreamWriter(fos, "UTF-8");
            out = new BufferedWriter(osw);


            //生成文件
            template.process(dataMap, out);

            out.flush();


        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
