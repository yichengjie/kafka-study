package com.yicj.study.mvc.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "hello")
public class HelloEndpoints {

    @ReadOperation
    public Map<String,Object> hello(){
        Map<String,Object> map = new HashMap<>() ;
        map.put("name", "yicj") ;
        return map ;
    }

}