package com.yicj.study.kafka.admin;

import com.yicj.study.kafka.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.time.Duration;
import java.util.*;

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
        // 这个配置会将订阅者的offset置为0，这样会接受队列中所有的消息
        // props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest") ;
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String, String> record : records){
                String format = String.format("=========> partition: %s,  offset = %s, key = %s, value = %s",
                        record.partition(), record.offset(), record.key(), record.value());
                System.err.println(format);
            }
            //Thread.sleep(10000);
        }
    }


    /**
     * 手动提交
     */
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


    /**
     * 手动提交每一个partition
     */
    @Test
    public void consumerTopicByPartition(){
        KafkaConsumer<String, String> consumer = initManualCommitConsumer();
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            for (TopicPartition partition: records.partitions()){
                // 从指定的partition中获取记录
                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                for (ConsumerRecord<String, String> partitionRecord: partitionRecords){
                    log.info("====================> partition: {},  offset = {}, key = {}, value = {}",
                            partitionRecord.partition(), partitionRecord.offset(), partitionRecord.key(), partitionRecord.value());
                }
                // 手动提交(获取最后一条消息的offset值)
                long lastOffset = partitionRecords.get(partitionRecords.size() -1).offset() ;
                // 单个partition中的offset，并提交
                Map<TopicPartition, OffsetAndMetadata> offsetAndMetadataMap = new HashMap<>() ;
                offsetAndMetadataMap.put(partition, new OffsetAndMetadata(lastOffset +1)) ;
                consumer.commitAsync(offsetAndMetadataMap, (offsets, exception) -> {
                    log.error("=====> commit async offsets :{} error : ", offsets, exception);
                });
                log.info("=======================partition : {}===============================", partition);
            }
        }
    }


    /**
     * 指定消费topic某一个分区
     */
    @Test
    public void consumerTopicWithPartition(){
        KafkaConsumer<String, String> consumer = initManualCommitConsumer();
        //consumer.subscribe(Arrays.asList(TOPIC_NAME));
        TopicPartition partition = new TopicPartition(TOPIC_NAME, 0) ;
        consumer.assign(Arrays.asList(partition));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String, String> record : records){
                log.info("=========> partition: {},  offset = {}, key = {}, value = {}",
                        record.partition(), record.offset(), record.key(), record.value());
                // 如果失败，则回滚，不要提交offset
            }
            // 手动通知offset提交
            consumer.commitAsync();
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
