package com.yicj.study.kafka.kafka;

import com.yicj.study.kafka.BaseJunit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: yicj
 * @date: 2023/4/9 14:38
 */
@Slf4j
public class HelloMessageProducerTest extends BaseJunit {

    @Autowired
    private HelloMessageProducer messageProducer ;

    @Test
    public void send(){
        messageProducer.send("111", "hello content");
    }

}
