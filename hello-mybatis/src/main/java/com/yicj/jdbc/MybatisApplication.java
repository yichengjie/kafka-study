package com.yicj.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Slf4j
@EnableAspectJAutoProxy
@EnableTransactionManagement
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {

        SpringApplication.run(MybatisApplication.class, args) ;

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory() ;
        Resource resource = new ClassPathResource("bean.xml") ;
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource) ;

        new ClassPathXmlApplicationContext("bean.xml") ;
    }
}
