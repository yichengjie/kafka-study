package com.yicj.study.service.impl;

import com.yicj.study.service.HelloService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * @author yicj
 * @date 2023年04月08日 19:27
 */
@Service
@CacheConfig(cacheNames = "common")
public class HelloServiceImpl implements HelloService {

    @Override
    @Cacheable(keyGenerator = "customCaffeineKeyGenerator")
    public String hello(String name) {
        String content = UUID.randomUUID().toString();
        return content + " : hello, " + name ;
    }
}
