package com.yicj.aop.context;


import com.yicj.aop.HelloAopApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloAopApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AutoConfigurationPackageTest {

    @Autowired
    private ApplicationContext context ;

    @Test
    public void printBasePackage(){
        // base package print
        String value = AutoConfigurationPackages.get(context).get(0);
        log.info("--------> base package : {}", value);
    }
}
