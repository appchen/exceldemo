package com.nubia.exceldemo.service.AnalysisStrategy;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 利用POI解析xlsx后缀的文件
 */
public class AnalysisExcelXlsx extends AnalysisExcelByPOI{
    public List<Map<Integer, List<String>>> analysisExcel(File file) {
        if(!super.checkFile(file)){
            System.out.println("文件不存在");
            return null;
        }
        Workbook workBook = null;
        FileInputStream fis = null;
        //返回的结果
        List<Map<Integer, List<String>>> sheets = null;
        try {
            fis = new FileInputStream(file);
            workBook = new XSSFWorkbook(fis);
            sheets = super.dealByPOI(workBook);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e2){
            e2.printStackTrace();
        } finally{
            //关流
            if (workBook != null || fis != null){
                try {
                    workBook.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return sheets;
    }
}
