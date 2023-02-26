package com.yicj.aop.config;

import com.yicj.aop.beans.Bar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarConfiguration {

    @Bean
    public Bar bbbar(){
        return new Bar() ;
    }

}
