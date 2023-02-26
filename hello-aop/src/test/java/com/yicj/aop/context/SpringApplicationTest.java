package com.yicj.aop.context;

import com.yicj.aop.beans.Bartender;
import com.yicj.aop.beans.Boss;
import com.yicj.aop.beans.spi.DemoDao;
import com.yicj.aop.config.TavernConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Slf4j
public class SpringApplicationTest {

    @Test
    public void basic(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TavernConfiguration.class) ;
        Boss bean = ctx.getBean(Boss.class);
        log.info("=====> bean : {}", bean);
    }

    @Test
    public void listAll(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TavernConfiguration.class) ;
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println); ;
        System.out.println("----------------------");
        Map<String, Bartender> bartenders = ctx.getBeansOfType(Bartender.class);
        bartenders.forEach((name, bartender) -> System.out.println(bartender));
    }

    @Test
    public void profile(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext() ;
        ctx.getEnvironment().setActiveProfiles("city");
        ctx.register(TavernConfiguration.class);
        ctx.refresh();
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Test
    public void spi(){
        SpringFactoriesLoader.loadFactories(DemoDao.class, this.getClass().getClassLoader())
                .forEach(System.out::println);
        System.out.println("-------------------------------");
        SpringFactoriesLoader.loadFactoryNames(DemoDao.class, this.getClass().getClassLoader())
                .forEach(System.out::println);

    }

}
