package com.yicj.aop.context;

import com.yicj.aop.beans.Bartender;
import com.yicj.aop.beans.Boss;
import com.yicj.aop.config.TavernConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Map;

@Slf4j
public class ApplicationTest {

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
}
