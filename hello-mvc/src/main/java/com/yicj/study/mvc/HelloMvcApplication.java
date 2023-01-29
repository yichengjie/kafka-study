package com.yicj.study.mvc;

import com.yicj.study.mvc.config.WithoutAnnotationConfiguration;
import com.yicj.study.mvc.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@EnableWebMvc
@SpringBootApplication
public class HelloMvcApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HelloMvcApplication.class);
        // 配置源配置
        Set<String> sources = new HashSet<>() ;
        sources.add(WithoutAnnotationConfiguration.class.getName()) ;
        application.setSources(sources);
        //
        ConfigurableApplicationContext context = application.run(args);
        WithoutAnnotationConfiguration bean = context.getBean(WithoutAnnotationConfiguration.class);
        log.info("bean : {}", bean);
        bean.getObjectProvider()
                .orderedStream()
                .forEach(HelloService::hello);
        HelloService helloService = context.getBean(HelloService.class);
        helloService.hello();
    }

}
