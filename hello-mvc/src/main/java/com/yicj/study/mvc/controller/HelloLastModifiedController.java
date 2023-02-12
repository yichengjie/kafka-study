package com.yicj.study.mvc.controller;

import com.yicj.study.mvc.util.DateTimeUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component("/helloLastModified")
public class HelloLastModifiedController extends AbstractController implements LastModified {

    private long lastModified ;

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        response.getWriter().write("<a href ''>this : "+format.format(new Date())+"</a>");
        return null;
    }

    @Override
    public long getLastModified(HttpServletRequest request) {
        // Spring 判断是否过期，通过判断请求的If-Modified-Since 是否大于等于当前getLastModified方法返回的时间戳，
        // 如果是，则认为没有修改过
        if (lastModified == 0L){
            LocalDateTime localDateTime = LocalDateTime.now().minusDays(1) ;
            lastModified = DateTimeUtils.toEpochMilli(localDateTime) ;
        }
        return lastModified;
    }
}
