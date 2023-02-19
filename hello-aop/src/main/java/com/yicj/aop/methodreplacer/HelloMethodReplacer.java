package com.yicj.aop.methodreplacer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
//@Component("helloMethodReplacer")
public class HelloMethodReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
        String content = format.format(new Date());
        log.info("======> HelloMethodReplacer method reimplement return value : {}", content);
        return content ;
    }
}
