package com.yicj.study.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KafkaConsumerService {

    //消费单条消息,topics 可以监听多个topic，如：topics = {"topic1", "topic2"}
    @KafkaListener(id = "consumerSingle", topics = "hello-kafka-test-topic")
    public void consumerSingle(String message){
        log.info("--------------------------------------");
        if(message.contains("error")){
            throw new RuntimeException("出错！！！！！") ;
        }
        log.info("consumerSingle ===> message : {}", message);
        log.info("--------------------------------------");
    }

    //批量消费消息
    @KafkaListener(id = "consumerBatch", topics = "hello-batch")
    public void consumerBatch(List<ConsumerRecord<String, String>> messages){
        log.info("consumerBatch ===> message size: {}", messages.size());
        log.info("messages: {}", messages);
    }

    //指定消费异常处理器
//    @KafkaListener(id = "consumerException", topics = "hello-kafka-test-topic", errorHandler = "consumerAwareListenerErrorHandler")
//    public void consumerException(String message){
//        throw new RuntimeException("consumer exception ") ;
//    }

    //验证ConsumerInterceptor
    @KafkaListener(id = "consumerInterceptor", topics = "consumer-interceptor")
    public void consumerInterceptor(String message){
        log.info("consumerInterceptor ====> {}", message);
    }
}
