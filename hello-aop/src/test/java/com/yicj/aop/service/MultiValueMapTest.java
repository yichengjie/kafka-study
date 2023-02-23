package com.yicj.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Slf4j
public class MultiValueMapTest {

    @Test
    public void test1(){
        MultiValueMap<String, String> result = new LinkedMultiValueMap<>() ;
        result.add("name", "张三");
        result.add("name", "李四");
        result.add("name", "王五");
        List<String> list = result.get("name");
        log.info("list : {}", list);
    }
}
