package com.yicj.study.client.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("测试集成Swagger")
@RestController
public class TestController {

    @ApiOperation(value = "计算+", notes = "加法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a",  value = "数字a", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "b",  value = "数字b", required = true, dataType = "Long")
    })
    @GetMapping("/add")
    public Integer add(Integer a, Integer b){
        return  a + b ;
    }

    @ApiOperation("test")
    @GetMapping("/test")
    public String prefixPath(){

        return "https to http" ;
    }
}
