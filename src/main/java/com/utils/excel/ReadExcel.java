package com.utils.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Created by GSN on 2017/4/1.
 * poi excel 读取
 */
public class ReadExcel {

    /**
     * 导入execl 自动验证版本
     *
     * @param path     项目根目录
     * @param fileName 文件名称
     * @return Map<String, Map<Integer, ListBehavior<String>>> ：Map(工作表, 数据(索引, 行))
     */
    public Object importExcel(String path, String fileName) {

        Object o = null;

        try {

            o = this.ImportExcel2003(path + fileName);

            if (o == null) {
                throw new RuntimeException("java poi excel 2003读取" + fileName + "失败了,正在尝试使用2007高版本运行...");
            }

        } catch (Exception e1) {

            e1.printStackTrace();// 2003导入异常信息

            try {

                o = this.ImportExcel2007(path + fileName);

                if (o == null) {
                    throw new RuntimeException("java poi excel 2007读取" + fileName + "失败了,一点办法没有了");
                }

            } catch (Exception e2) {

                e2.printStackTrace();// 2007导入异常信息

            }

        }

        return o;

    }

    /**
     * 导入2003版本
     *
     * @param pathName 路径 + 文件名
     * @return 数据
     */
    private Object ImportExcel2003(String pathName) {

        Map<String, Map<Integer, List<String>>> ms = new HashMap<String, Map<Integer, List<String>>>();
        InputStream is = null;
        HSSFWorkbook hwb = null;

        try {

            is = new FileInputStream(pathName);
            hwb = new HSSFWorkbook(is);

            // 读取所有工作表
            for (int i = 0; i < hwb.getNumberOfSheets(); i++) {

                Map<Integer, List<String>> m = new HashMap<Integer, List<String>>();// 每个excel工作表中的表结构

                HSSFSheet sheet = hwb.getSheetAt(i);

                if (sheet != null) {

                    // 获取j行
                    for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {

                        List<String> l = new ArrayList<String>();// 存放每行

                        HSSFRow row = sheet.getRow(j);

                        if (row != null) {

                            // 获取k列
                            for (int k = 0; k < row.getLastCellNum(); k++) {

                                HSSFCell cell = row.getCell(k);

                                if (cell != null) {

                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    String val = cell.getRichStringCellValue().toString();

                                    if (val != null && !val.equals("")) {
                                        l.add(val);// 存放每列中的数据
                                    }

                                }

                            }

                            if (l.size() != 0) {
                                m.put(j, l);// 存放每行中的数据
                            }

                        }

                    }

                    ms.put(sheet.getSheetName(), m);// 存放每个工作表的数据

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {

            if (hwb != null) {
                try {
                    hwb.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return ms;

    }

    /**
     * 导入2007版本
     *
     * @param pathName 路径 + 文件名
     * @return 数据
     */
    private Object ImportExcel2007(String pathName) {

        Map<String, Map<Integer, List<String>>> ms = new HashMap<String, Map<Integer, List<String>>>();// 所有excel工作表
        InputStream is = null;
        XSSFWorkbook xwb = null;

        try {

            is = new FileInputStream(pathName);
            xwb = new XSSFWorkbook(is);

            // 读取所有工作表
            for (int i = 0; i < xwb.getNumberOfSheets(); i++) {

                Map<Integer, List<String>> m = new HashMap<Integer, List<String>>();// 每个excel工作表中的表结构

                XSSFSheet sheet = xwb.getSheetAt(i);

                if (sheet != null) {

                    // 获取j行
                    for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {

                        List<String> l = new ArrayList<String>();// 存放每行

                        XSSFRow row = sheet.getRow(j);

                        if (row != null) {
                            // 获取k列
                            for (int k = 0; k < row.getLastCellNum(); k++) {

                                XSSFCell cell = row.getCell(k);

                                if (cell != null) {

                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    String val = cell.getRichStringCellValue().toString().replace(" ", "");

                                    if (val != null && !val.equals("")) {
                                        l.add(val);// 存放每列中的数据
                                    }

                                }

                            }

                            if (l.size() != 0) {
                                m.put(j, l);// 存放每行中的数据
                            }

                        }

                    }

                    ms.put(sheet.getSheetName(), m);// 存放每个工作表的数据

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {

            if (xwb != null) {
                try {
                    xwb.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return ms;

    }

}
