package com.yicj.study.mvc.controller;

import com.yicj.study.mvc.util.DateTimeUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("/download")
public class DownloadController extends AbstractController implements LastModified {

    private Map<String, Long> cacheMap = new ConcurrentHashMap<>() ;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = request.getParameter("name");
        ClassPathResource resource = new ClassPathResource("static/images/" + fileName) ;
        File file = resource.getFile();
        InputStream inputStream = resource.getInputStream();
        byte[] data = new byte[(int) file.length()];
        int length = inputStream.read(data);
        inputStream.close();

        String outFileName = URLEncoder.encode(fileName, "UTF-8");

        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + outFileName + "\"");
        response.addHeader("Content-Length", "" + data.length);

        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
        return null;
    }

    @Override
    public long getLastModified(HttpServletRequest request) {
        String fileName = request.getParameter("name");
        Long aLong = cacheMap.get(fileName);
        if (aLong == null){
            LocalDateTime localDateTime = LocalDateTime.now().minusDays(1) ;
            aLong = DateTimeUtils.toEpochMilli(localDateTime) ;
            cacheMap.putIfAbsent(fileName, aLong) ;
        }
        return aLong;
    }
}
