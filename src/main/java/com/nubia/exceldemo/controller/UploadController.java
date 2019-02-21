package com.nubia.exceldemo.controller;

import com.nubia.exceldemo.service.AnalysisService;
import com.nubia.exceldemo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class UploadController {

    @Autowired
    private AnalysisService analysisService;

    @PostMapping(path="/upload/")
    public String uploadController(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return "0";
        }
        String fileName = file.getOriginalFilename();
        long size = file.getSize();
        System.out.println(fileName+" size: "+size);

        String filePath = "D:\\uploadFile\\";

        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            File newFile = new File(filePath+fileName);
            analysisService.analysisExcel(newFile);
        }catch(Exception e){
            e.printStackTrace();
        }

        return "1";
    }
}
