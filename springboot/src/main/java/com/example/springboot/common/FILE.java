package com.example.springboot.common;

import org.apache.catalina.valves.JsonErrorReportValve;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FILE {
    public static  String UPLOAD_PATH=" ";
//            "C:\\Users\\lin17\\IdeaProjects\\msorder\\springboot\\src\\main\\resources\\static\\";

    public static String uploadFile(MultipartFile file) throws IOException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.err.println("src/main/resources/static");
        UPLOAD_PATH=path.substring(1,path.length()-15)+"src/main/resources/static/";

        final String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String originalFilename = file.getOriginalFilename();

        String fileName = StringUtils.substringBeforeLast(originalFilename,".");
//        Date date = new Date();
//        DateFormat df = new SimpleDateFormat("yyyymmddhhmmss");
//
//        String strDate = df.format(date);
        String filename = fileName+"."+fileSuffix;
        File descFile = new File(UPLOAD_PATH + filename);
        file.transferTo(descFile);
        String url = "/upload"+filename;
        return url;



    }
}
