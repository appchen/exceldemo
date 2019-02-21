package com.nubia.exceldemo.service.AnalysisStrategy;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 利用POI解析xls后缀的文件
 */
public class AnalysisExcelXls extends AnalysisExcelByPOI{

    public List<Map<Integer, List<String>>> analysisExcel(File file) {
        if(!super.checkFile(file)){
            System.out.println("文件不存在");
            return null;
        }
        FileInputStream fis = null;
        Workbook workBook = null;
        //返回的结果
        List<Map<Integer, List<String>>> sheets = null;
        try {
            fis = new FileInputStream(file);
            workBook = new HSSFWorkbook(fis, true);
            sheets = super.dealByPOI(workBook);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e2){
            e2.printStackTrace();
        } finally{
            //关流
            if (fis != null || workBook != null){
                try {
                    fis.close();
                    workBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return sheets;
    }
}
