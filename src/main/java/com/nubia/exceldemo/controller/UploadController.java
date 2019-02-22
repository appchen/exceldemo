package com.nubia.exceldemo.controller;

import com.alibaba.fastjson.JSON;
import com.nubia.exceldemo.model.Course;
import com.nubia.exceldemo.model.ResultSet;
import com.nubia.exceldemo.service.AnalysisService;
import com.nubia.exceldemo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
public class UploadController {

    @Autowired
    private AnalysisService analysisService;

    @PostMapping(path="/upload/")
    public String uploadController(@RequestParam("file") MultipartFile file){
        ResultSet set = new ResultSet();
        if (file.isEmpty()){
            set.setState(0);
            return JSON.toJSONString(set);
        }
        String fileName = file.getOriginalFilename();
        long size = file.getSize();
        System.out.println(fileName+" size: "+size);

        String filePath = "D:\\uploadFile\\";

        List<Course> courses = null;
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            File newFile = new File(filePath+fileName);
            //analysisService.analysisExcel(newFile);
            courses = analysisService.saveExcel(newFile);

        }catch(Exception e){
            set.setState(0);
            return JSON.toJSONString(set);
        }
        if (courses == null){
            set.setState(0);
        }else{
            set.setState(1);
            set.setCourses(courses);
        }

        return  JSON.toJSONString(set);

    }
}
