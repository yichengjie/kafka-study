package com.yicj.aop;

import com.yicj.aop.service.HelloServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@EnableWebMvc
@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class HelloAopApplication {

    private static final String BEAN = AutoConfigurationPackages.class.getName();

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HelloAopApplication.class, args);
        HelloServiceManager manager = context.getBean(HelloServiceManager.class);
        manager.hello();
    }
}
