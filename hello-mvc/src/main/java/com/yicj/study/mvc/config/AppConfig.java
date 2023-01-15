package com.yicj.study.mvc.config;

import com.yicj.study.mvc.service.HelloService;
import com.yicj.study.mvc.service.impl.HelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration/*(proxyBeanMethods = false)*/
public class AppConfig {

    @Bean
    public HelloService helloService(){
        return new HelloServiceImpl() ;
    }
}
