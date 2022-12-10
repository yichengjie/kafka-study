package com.yicj.study.webflux.config;

import com.yicj.study.webflux.handler.HelloHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class AppConfig {

    @Autowired
    private HelloHandler helloHandler ;

    @Bean
    public RouterFunction<ServerResponse> userRouter(){
        return RouterFunctions
                .route(GET("/hello/getUserById"), helloHandler::getUserId);

    }
}
