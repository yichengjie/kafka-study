package com.yicj.aop.config;

import com.yicj.aop.beans.Bartender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("city")
public class BartenderConfiguration {

    @Bean
    public Bartender zhangxiaosan(){
        return new Bartender("张小三") ;
    }

    @Bean
    public Bartender zhangdasan(){
        return new Bartender("张大三") ;
    }
}

