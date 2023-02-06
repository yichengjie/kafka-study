package com.yicj.aop.service;

import com.yicj.aop.HelloAopApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloAopApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HelloServiceTest {

    @Autowired
    private HelloService helloService ;

    @Test
    public void hello(){
        String retValue = helloService.hello();
        log.info("---> ret value : {}", retValue);
    }
}
