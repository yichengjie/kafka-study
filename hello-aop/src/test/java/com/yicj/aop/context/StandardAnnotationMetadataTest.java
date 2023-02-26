package com.yicj.aop.context;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;


@Slf4j
public class StandardAnnotationMetadataTest {

    @EnableAutoConfiguration
    static class Anno{

    }
    @Test
    public void from(){
        EnableAutoConfiguration annotation = Anno.class.getAnnotation(EnableAutoConfiguration.class);
        StandardAnnotationMetadata metadata =  new StandardAnnotationMetadata(Anno.class, true);
        Map<String, Object> attributes = metadata.getAnnotationAttributes(EnableAutoConfiguration.class.getName());
        attributes.forEach((key, value) -> {
            log.info("=====> key : {}, value: {}", key, value);
        });
    }


}
