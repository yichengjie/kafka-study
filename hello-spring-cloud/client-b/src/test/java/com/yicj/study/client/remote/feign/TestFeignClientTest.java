package com.yicj.study.client.remote.feign;

import com.yicj.study.client.BaseJunitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TestFeignClientTest extends BaseJunitTest {

    @Autowired
    private TestFeignClient testFeignClient ;

    @Test
    public void add(){
        Integer a = 1 ;
        Integer b = 2 ;
        Integer result = testFeignClient.add(a, b);
        log.info("result : {}", result);
    }
}
