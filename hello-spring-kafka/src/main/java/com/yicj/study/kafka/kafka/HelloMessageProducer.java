package com.yicj.study.kafka.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HelloMessageProducer {

    @Value("${hdk.kafka.topic.helloTopic}")
    private String topicName ;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate ;

    public void send(String key, String content){
        kafkaTemplate.send(topicName, key, content) ;
    }
}