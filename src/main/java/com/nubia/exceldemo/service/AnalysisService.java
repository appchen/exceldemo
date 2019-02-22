package com.nubia.exceldemo.service;

import com.nubia.exceldemo.dao.CourseDAO;
import com.nubia.exceldemo.model.Course;
import com.nubia.exceldemo.service.AnalysisStrategy.AnalysisExcelCsv;
import com.nubia.exceldemo.service.AnalysisStrategy.AnalysisExcelStrategy;
import com.nubia.exceldemo.service.AnalysisStrategy.AnalysisExcelXls;
import com.nubia.exceldemo.service.AnalysisStrategy.AnalysisExcelXlsx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class AnalysisService {

    @Autowired
    private CourseDAO courseDAO;

    /**
     * 保存Excel文件 成功返回1  失败返回0
     * @param file
     * @return
     */
    public List<Course> saveExcel(File file){
        List<Map<Integer, List<String>>> tables = analysisExcel(file);
        List<Course> courses = new ArrayList<Course>();
        if (tables == null){
            return null;
        }
        for (Map<Integer, List<String>> table : tables){
            if (table == null){
                continue;
            }
            String pre = null;
            for (int i=0; i<table.size(); i++){
                if (i == 0 && table.get(0).size() != 4){
                    System.out.println("文件格式不对");
                    return null;
                }
                if (i == 0){ // 第一行为通用名称 无须保存
                    continue;
                }
                List<String> rowData = table.get(i);
                Course course = new Course();
                if (rowData.size() == 4){
                    pre = rowData.get(0);
                    course.setSeries(rowData.get(0));
                    course.setCname(rowData.get(1));
                    course.setResource(rowData.get(2));
                    course.setDetail(rowData.get(3));
                }else if(rowData.size() == 3){
                    course.setSeries(pre);
                    course.setCname(rowData.get(0));
                    course.setResource(rowData.get(1));
                    course.setDetail(rowData.get(2));
                }else{
                    continue;
                }
                // 保存至数据库
                courseDAO.saveCourseDao(course);
                courses.add(course);
            }
        }
        return courses;
    }

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
