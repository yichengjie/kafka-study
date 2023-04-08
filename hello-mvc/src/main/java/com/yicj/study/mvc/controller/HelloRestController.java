package com.yicj.study.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yicj
 * @date 2023年04月08日 18:59
 */
@Slf4j
@RestController
public class HelloRestController {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor ;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name){
        taskExecutor.execute(() -> log.info("===> 定义线程池执行任务....."));
        return "hello, " + name ;
    }
}
