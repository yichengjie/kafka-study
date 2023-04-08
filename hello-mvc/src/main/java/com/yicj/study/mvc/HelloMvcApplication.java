package com.yicj.study.mvc;

import com.yicj.hdk.accesslog.EnableHdkAccessLog;
import com.yicj.hdk.accesslog.HdkAccessLogType;
import com.yicj.hdk.swagger.EnableHdkSwagger;
import com.yicj.hdk.threadpool.EnableHdkThreadPool;
import com.yicj.study.mvc.config.AppConfig;
import com.yicj.study.mvc.config.WithoutAnnotationConfiguration;
import com.yicj.study.mvc.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@SpringBootApplication
@EnableHdkThreadPool
@EnableHdkSwagger(value = "com.yicj.study.mvc.controller", activeProfiles = "dev")
@EnableHdkAccessLog(value = "com.yicj.study.mvc.controller", type = HdkAccessLogType.ALL)
public class HelloMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloMvcApplication.class, args) ;

//        SpringApplication application = new SpringApplication(HelloMvcApplication.class);
//        // 配置源配置
//        Set<String> sources = new HashSet<>() ;
//        sources.add(WithoutAnnotationConfiguration.class.getName()) ;
//        application.setSources(sources);
//        //
//        ConfigurableApplicationContext context = application.run(args);
//        HelloService helloService = context.getBean(HelloService.class);
//        helloService.hello();

        // ObjectProvider test
//        WithoutAnnotationConfiguration bean = context.getBean(WithoutAnnotationConfiguration.class);
//        log.info("bean : {}", bean);
//        bean.getObjectProvider()
//                .orderedStream()
//                .forEach(HelloService::hello);

//        AppConfig appConfig = context.getBean(AppConfig.class);
//        log.info("app config : {}", appConfig);
//        BeanFactoryAware aware = (BeanFactoryAware) appConfig ;
//        aware.setBeanFactory(null);
    }


    public void test(String name, Hello hello){
        hello.hello(name) ;
    }


    interface Hello{

        String hello(String name) ;
    }

}
