package com.yicj.aop.config;

import com.yicj.aop.beans.Bar;
import com.yicj.aop.condition.ExistBossCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarConfiguration {

    @Bean
    @Conditional(ExistBossCondition.class)
    public Bar bbbar(){
        return new Bar() ;
    }

}
