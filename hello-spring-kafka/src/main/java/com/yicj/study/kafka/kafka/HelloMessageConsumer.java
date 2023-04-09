package com.yicj.study.kafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloMessageConsumer {


    @KafkaListener(
            id = "helloMessageConsumer",
            groupId = "${hdk.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory",
            topics = "${hdk.kafka.topic.helloTopic}"
    )
    public void listener(ConsumerRecord<String, Object> record, Acknowledgment ack) {
        String content = (String) record.value();
        try {
            log.info("kafka message : {}", content) ;
            // todo 业务方法处理
        } catch (Exception e) {
            log.error("消息异常信息: ", e);
        } finally {
            ack.acknowledge();
        }
    }
}