package com.nubia.exceldemo.service.AnalysisStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 解析csv后缀的文件
 */
public class AnalysisExcelCsv extends AbstractAnalysisExcel{
    public List<Map<Integer, List<String>>> analysisExcel(File file) {
        if (file == null || !file.exists()){
            System.out.println("文件不存在");
            return null;
        }
        //返回的结果
        List<Map<Integer, List<String>>> sheets = new ArrayList<Map<Integer, List<String>>>();
        Map<Integer, List<String>> table = new HashMap<Integer, List<String>>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            int row = 0;
            while ((line = bufferedReader.readLine()) != null) { // 逐行读取
                List<String> rowDatas = splitLine(line);
                table.put(row, rowDatas);
                row++;
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        sheets.add(table);
        return sheets;
    }

    /**
     * 根据,切分字符串，生成List
     * @param line
     * @return
     */
    private List<String> splitLine(String line){
        if (line == null){
            return null;
        }
        String[] s = line.split(",");
        List<String> list = Arrays.asList(s);
        return list;
    }
}
