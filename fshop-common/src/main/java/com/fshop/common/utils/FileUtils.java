package com.fshop.common.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Slf4j
@Component
@RequiredArgsConstructor
public class FileUtils {

    //重命名文件，根据时间
    public String fileRename(MultipartFile file){

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取当前的日期
        LocalDateTime now = LocalDateTime.now();
        //转换为字符串并拼接文件名

        String newFileName = now.toString() + suffixName;
        return newFileName;
    }
}
