package com.yicj.study.kafka.admin;

import com.yicj.study.kafka.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class ConsumerSampleTest {

    private final static String TOPIC_NAME = "hello-topic" ;

    @Test
    public void autoCommitConsumerTopic() throws InterruptedException {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.BOOTSTRAP_SERVER_ADDRESS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String, String> record : records){
                log.info("=========> partition: {},  offset = {}, key = {}, value = {}",
                        record.partition(), record.offset(), record.key(), record.value());
            }
            Thread.sleep(10000);
        }
    }


    @Test
    public void consumerTopic(){
        KafkaConsumer<String, String> consumer = initManualCommitConsumer();
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String, String> record : records){
                log.info("=========> partition: {},  offset = {}, key = {}, value = {}",
                        record.partition(), record.offset(), record.key(), record.value());
                // 如果失败，则回滚，不要提交offset
            }
            // 手动通知offset提交
            //consumer.commitAsync();
        }
    }


    private KafkaConsumer<String, String> initManualCommitConsumer(){
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.BOOTSTRAP_SERVER_ADDRESS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer ;
    }
}
