package com.yicj.study.mvc.methodreplacer;

import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HelloMethodReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
        return format.format(new Date());
    }
}
