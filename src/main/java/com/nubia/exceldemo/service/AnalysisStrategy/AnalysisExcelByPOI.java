package com.nubia.exceldemo.service.AnalysisStrategy;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AnalysisExcelByPOI extends AbstractAnalysisExcel{

    public abstract List<Map<Integer, List<String>>> analysisExcel(File file);

    /**
     * 验证file是否存在
     * @param file
     * @return
     */
    public boolean checkFile(File file){
        if (file == null || !file.exists()){
            return false;
        }
        return true;
    }

    /**
     * 根据传入的workBook处理Excel文件
     * @param workBook
     * @return
     */
    public List<Map<Integer, List<String>>> dealByPOI(Workbook workBook){
        //存放结果
        List<Map<Integer, List<String>>> sheets = new ArrayList<Map<Integer, List<String>>>();
        if(workBook == null){
            return null;
        }
        int numberOfSheets = workBook.getNumberOfSheets();
        // 遍历所有sheets
        for (int i=0; i<numberOfSheets; i++){
            Sheet sheet = workBook.getSheetAt(i);
            Map<Integer, List<String>> table = new HashMap<Integer, List<String>>();
            int j = 0;
            // 遍历每行
            for (Row row : sheet){
                table.put(j, new ArrayList<String>());
                // 遍历每列
                for(Cell cell : row){
                    switch (cell.getCellType()) {
                        case STRING:
                            table.get(j).add(cell.getRichStringCellValue().getString());
                            break;
                        case _NONE:
                            break;
                        case NUMERIC:
                            table.get(j).add(cell.getNumericCellValue() + "");
                            break;
                        case BOOLEAN:
                            break;
                        case FORMULA: // 公式类型
                            //table.get(j).add(cell.getCellFormula());
                            //System.out.println(1);
                            break;
                        case BLANK: // 空值
                            break;
                        case ERROR:
                            break;
                    }
                }
                j++;
            }
            sheets.add(table);
        }
        return sheets;
    }
}
