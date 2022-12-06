package com.yicj.study.kafka.admin;

import com.yicj.study.kafka.constants.CommonConstants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class SSLConsumerSampleTest {

    private final static String TOPIC_NAME = "hello-topic" ;

    @Test
    public void autoCommitConsumerTopic() throws InterruptedException {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.SSL_BOOTSTRAP_SERVER_ADDRESS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        // 这个配置会将订阅者的offset置为0，这样会接受队列中所有的消息
        // props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest") ;
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // ssl config
        props.put("security.protocol","SSL");
        props.put("ssl.endpoint.identification.algorithm","");
        props.put("ssl.truststore.location","client.truststore.jks");
        props.put("ssl.truststore.password","hello123");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String, String> record : records){
                String format = String.format("=========> partition: %s,  offset = %s, key = %s, value = %s",
                        record.partition(), record.offset(), record.key(), record.value());
                System.err.println(format);
            }
        }
    }
}
