package com.yicj.study.mvc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MarkByConfiguration {


    @Bean
    public A a(){
        return new A() ;
    }

    @Bean
    public B b (){
        a() ;
        return new B() ;
    }

    class A{
        public A(){
            log.info("========> A created ....");
        }
    }

    class B{
        public B(){
            log.info("B created ....");
        }
    }
}
