package com.yicj.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class HelloAopApplication {

    public static void main(String[] args) {

        SpringApplication.run(HelloAopApplication.class, args) ;
    }
}
