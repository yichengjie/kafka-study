package com.yicj.study.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class KafkaProducerService {

    @Autowired
    @Qualifier("kafkaTemplate")
    private KafkaTemplate<String, String> kafkaTemplate ;

    public void sendMessageSync(String topic, String key, String message)
            throws ExecutionException, InterruptedException, TimeoutException {
        kafkaTemplate.send(topic, key,message).get(10, TimeUnit.SECONDS) ;
        log.info("sendMessageSync ==> topic: {}, key: {}, message: {}", topic, key, message);
    }

    public void sendMessageGetResult(String topic, String key, String message) throws ExecutionException, InterruptedException {
        SendResult<String, String> result = kafkaTemplate.send(topic, key, message).get();
        log.info("sendMessageSync ==> topic: {}, key: {}, message: {}", topic, key, message);
        log.info("The partition the message was sent to : {}", result.getRecordMetadata().partition());
    }


    public void sendMessageAsync(String topic, String key, String message){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("sendMessageAsync failure ! topic : {}, key: {}, message : {}", topic, key, message);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("sendMessageAsync success ! topic : {}, key: {}, message: {}", topic, key, message);
            }
        });
    }

}
