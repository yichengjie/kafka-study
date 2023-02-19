package com.yicj.aop.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Slf4j
@Data
// @Component
public class HelloServiceManager {

    @Autowired
    private Map<String, HelloService> helloServiceMap ;

    public void hello(){
        helloServiceMap.forEach((key, value) -> {
            log.info("=====> bean name : {}", key) ;
            String ret = value.hello();
            log.info("manager service ret value : {}", ret);
        });
    }
}
