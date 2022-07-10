package com.yicj.study.config;

import com.yicj.study.constants.AppConstants;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

//配置主题（KafkaAdmin）
//@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> props = new HashMap<>() ;
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.BOOTSTRAP_SERVERS) ;
        return new KafkaAdmin(props) ;
    }

    @Bean
    public NewTopic newTopic(){
        return new NewTopic("hello-kafka-test-topic", 3, (short) 2) ;
    }

}
