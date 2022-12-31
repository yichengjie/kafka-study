package com.yicj.study.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;

@ConditionalOnWebApplication
@SpringBootApplication
public class HelloMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloMvcApplication.class, args) ;
    }
}
