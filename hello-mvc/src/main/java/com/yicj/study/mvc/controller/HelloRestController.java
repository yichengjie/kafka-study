package com.yicj.study.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yicj
 * @date 2023年04月08日 18:59
 */
@RestController
public class HelloRestController {

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name){
        return "hello, " + name ;
    }
}
