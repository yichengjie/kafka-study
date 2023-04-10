package com.yicj.study.mvc.service.impl;

import com.yicj.study.mvc.service.HelloService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Data
@Slf4j
// @Component
public class HelloServiceImpl implements HelloService {

    @Value("${admin.name}")
    private String name ;

    @Value("${admin.age}")
    private Integer age ;

    @Override
    public String hello() {
        log.info("---> hello impl ...");
        return "hello " ;
    }
}
