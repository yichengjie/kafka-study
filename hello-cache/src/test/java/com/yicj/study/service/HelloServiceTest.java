package com.yicj.study.service;

import com.yicj.study.BaseJunit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yicj
 * @date 2023年04月08日 19:31
 */
@Slf4j
public class HelloServiceTest extends BaseJunit {

    @Autowired
    private HelloService helloService ;

    @Test
    public void hello() throws InterruptedException {
        System.out.println(helloService.hello("张三"));
        Thread.sleep(1000);
        System.out.println(helloService.hello("张三"));
        Thread.sleep(1100);
        System.out.println(helloService.hello("张三"));
    }
}
