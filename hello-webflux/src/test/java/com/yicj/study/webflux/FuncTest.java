package com.yicj.study.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FuncTest {

    @Test
    public void test() throws InterruptedException {
        List<String> handlerMappings = new ArrayList<>() ;
        handlerMappings.add("Java");
        handlerMappings.add("PHP");
        handlerMappings.add("C++");
        Flux.fromIterable(handlerMappings)
                .flatMap(handlerMapping -> this.getHandler(handlerMapping))
                .next().subscribe(value -> log.info("value : {}", value)); ;
                //.switchIfEmpty(createNotFoundError())
                //.flatMap(handler -> invokeHandler(exchange, handler))
                //.flatMap(result -> handleResult(exchange, result));
        Thread.sleep(1000);
    }


    private Flux<String> getHandler(String handlerMapping){
        if ("PHP".equals(handlerMapping)){
            String[] split = handlerMapping.split("");
            return Flux.just(split) ;
        }
        return Flux.empty() ;
    }
}
