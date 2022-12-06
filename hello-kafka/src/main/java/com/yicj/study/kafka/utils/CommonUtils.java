package com.yicj.study.kafka.utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Optional;

public class CommonUtils {

    /**
     * 将classpath下的文件复制一个临时文件
     * @param classPathFile classpath下的文件
     * @return 临时文件
     * @throws IOException
     */
    public static File copyClassPathTempFile(String classPathFile, String defaultTempFileName) {
        String tempPath = Optional.ofNullable(defaultTempFileName).orElseGet(() -> IdGen.uuid() + ".jks")  ;
        File tempFile = new File(tempPath);
        if (!tempFile.exists()){
            try {
                tempFile.createNewFile();
            }catch (IOException e){
                throw new RuntimeException("新建文件【"+tempPath+"】失败!") ;
            }
        }
        Resource resource = new ClassPathResource(classPathFile);
        try(InputStream inputStream = resource.getInputStream();
            OutputStream outputStream = new FileOutputStream(tempFile)) {
            IOUtils.copy(inputStream, outputStream);
            return tempFile;
        }catch (IOException e){
            throw new RuntimeException("复制的"+classPathFile+"文件不存在！") ;
        }
    }
}
