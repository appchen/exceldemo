package com.nubia.exceldemo.service.AnalysisStrategy;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 解析Excel策略类接口使用类
 */
public class AnalysisExcelStrategy {
    private AbstractAnalysisExcel analysisExcel = null;

    public AnalysisExcelStrategy(AbstractAnalysisExcel analysisExcel){
        this.analysisExcel = analysisExcel;
    }

    /**
     * 解析Excel的接口方法
     * 参数为传入的file文件
     * 返回值 第一个List表示一个workbook包含的sheet
     * List里面的Map表示一个sheet 即一张表
     * Map<Integer, List<String>> Integer表示第几行 List<String>表示该行的所有Cell
     * @param file
     * @return
     */
    public List<Map<Integer, List<String>>> analysisExcel(File file){
        if (analysisExcel == null) {
            return null;
        }
        return analysisExcel.analysisExcel(file);
    }
}
