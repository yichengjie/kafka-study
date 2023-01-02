package com.yicj.study.mvc.service.impl;

import com.yicj.study.mvc.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public void hello() {
        log.info("---> hello impl ...");
    }
}
