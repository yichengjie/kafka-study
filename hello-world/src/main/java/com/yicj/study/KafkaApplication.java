package com.yicj.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {

        SpringApplication.run(KafkaApplication.class, args) ;
    }
}
