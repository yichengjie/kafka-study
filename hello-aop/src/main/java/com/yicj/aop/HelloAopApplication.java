package com.yicj.aop;

import com.yicj.aop.runlistener.HelloRunnerListener;
import com.yicj.aop.service.HelloServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Slf4j
@EnableWebMvc
@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class HelloAopApplication {

    private static final String BEAN = AutoConfigurationPackages.class.getName();

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HelloAopApplication.class);
        application.setMainApplicationClass(HelloAopApplication.class);
        //ConfigurableApplicationContext context = SpringApplication.run(HelloAopApplication.class, args);
        ConfigurableApplicationContext context = application.run(args) ;
        HelloServiceManager manager = context.getBean(HelloServiceManager.class);
        manager.hello();

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        String[] postProcessorNames =
                beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);

        Arrays.stream(postProcessorNames).forEach(name -> log.info("-------------> name : {}", name));
        log.info("==============================================");
        String[] postProcessorNames2 =
                beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);

        Arrays.stream(postProcessorNames2).forEach(name -> log.info("-----------> name : {}", name));


    }
}
