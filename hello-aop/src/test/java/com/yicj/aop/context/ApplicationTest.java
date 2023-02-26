package com.yicj.aop.context;

import com.yicj.aop.beans.Boss;
import com.yicj.aop.config.TavernConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class ApplicationTest {

    @Test
    public void basic(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TavernConfiguration.class) ;
        Boss bean = ctx.getBean(Boss.class);
        log.info("=====> bean : {}", bean);
    }
}
