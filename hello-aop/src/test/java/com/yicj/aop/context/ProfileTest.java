package com.yicj.aop.context;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileTest {

    @Test
    public void profile(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext() ;
        // 为ApplicationContext的环境设置正在激活的Profile
        //ctx.getEnvironment().setActiveProfiles("city");
        //ctx.register();

    }
}
