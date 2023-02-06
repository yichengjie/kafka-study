package com.yicj.aop.service.impl;

import com.yicj.aop.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("helloServiceImpl")
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        return "hello world";
    }
}
