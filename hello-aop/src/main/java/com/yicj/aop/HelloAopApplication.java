package com.yicj.aop;

import com.yicj.aop.service.HelloServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class HelloAopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HelloAopApplication.class, args);
        HelloServiceManager manager = context.getBean(HelloServiceManager.class);
        manager.hello();
    }
}
