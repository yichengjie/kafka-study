package com.yicj.study.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.logging.LogLevel;
import org.springframework.core.env.Environment;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class BinderTest extends BaseJunitTest{

    @Autowired
    private Environment environment ;

    @Test
    public void test1(){
        Binder binder = Binder.get(environment);
        ConfigurationPropertyName LOGGING_LEVEL = ConfigurationPropertyName.of("logging.level");
        Bindable<Map<String, LogLevel>> STRING_LOGLEVEL_MAP = Bindable.mapOf(String.class, LogLevel.class) ;
        Map<String, LogLevel> levels = binder.bind(LOGGING_LEVEL, STRING_LOGLEVEL_MAP).orElseGet(Collections::emptyMap);
        log.info("====> levels : {}", levels);
    }
}
