package com.yicj.aop.config;

import com.yicj.aop.service.HelloServiceManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public HelloServiceManager helloServiceManager(){

        return new HelloServiceManager() ;
    }
}
