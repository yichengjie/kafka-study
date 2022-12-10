package com.yicj.study.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/add")
    public Integer add(Integer a, Integer b){
        return  a + b ;
    }

    @GetMapping("/test")
    public String prefixPath(){

        return "https to http" ;
    }
}
