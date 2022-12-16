package com.yicj.study.config;

import com.yicj.study.config.initializer.HelloApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GitConfigClientApplication  {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GitConfigClientApplication.class);
        //springApplication.addInitializers(new HelloApplicationContextInitializer());
        springApplication.run(args) ;
    }
}
