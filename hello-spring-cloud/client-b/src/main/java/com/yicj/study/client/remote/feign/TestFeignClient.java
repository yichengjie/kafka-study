package com.yicj.study.client.remote.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "testFeignClient", name = "client-a")
public interface TestFeignClient {

    @GetMapping("/add")
    Integer add(
            @RequestParam("a") Integer a,
            @RequestParam("b") Integer b) ;

}
