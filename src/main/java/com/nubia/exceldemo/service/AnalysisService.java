package com.nubia.exceldemo.service;

import com.nubia.exceldemo.service.AnalysisStrategy.AnalysisExcelCsv;
import com.nubia.exceldemo.service.AnalysisStrategy.AnalysisExcelStrategy;
import com.nubia.exceldemo.service.AnalysisStrategy.AnalysisExcelXls;
import com.nubia.exceldemo.service.AnalysisStrategy.AnalysisExcelXlsx;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AnalysisService {

    public List<Map<Integer, List<String>>> analysisExcel(File file){
        if (file == null || !file.exists()) {
            return null;
        }
        //判断文件的后缀名 采用不同的策略
        String fileName = file.getName();
        String suffixName = fileName.substring(fileName.lastIndexOf('.')+1);
        List<Map<Integer, List<String>>> list = null;
        AnalysisExcelStrategy analysisExcelStrategy = null;
        if ("xls".equals(suffixName)){
            analysisExcelStrategy = new AnalysisExcelStrategy(new AnalysisExcelXls());
        }else if ("xlsx".equals(suffixName)){
            analysisExcelStrategy = new AnalysisExcelStrategy(new AnalysisExcelXlsx());
        }else if ("csv".equals(suffixName)){
            analysisExcelStrategy = new AnalysisExcelStrategy(new AnalysisExcelCsv());
        }

        if (analysisExcelStrategy != null){
            list = analysisExcelStrategy.analysisExcel(file);
        }

        show(list);

        return list;
    }

    /**
     * 测试方法 展示
     * @param list
     */
    public void show(List<Map<Integer, List<String>>> list){
        if (list == null){
            System.out.println("无Excel数据");
            return ;
        }
        for (Map<Integer, List<String>> table : list){
            System.out.println("-------------------------------");
            Set<Integer> keys = table.keySet();
            Iterator<Integer> it = keys.iterator();
            while(it.hasNext()){
                List<String> rows = table.get(it.next());
                for(String s : rows){
                    System.out.print(s+"     ");
                }
                System.out.println();
            }
            System.out.println("-------------------------------");
        }
    }

}
