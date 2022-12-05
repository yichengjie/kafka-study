package com.yicj.study.kafka.admin;

import com.yicj.study.kafka.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.junit.Test;
import java.util.Properties;
import java.util.concurrent.Future;
 import java.util.stream.IntStream;

@Slf4j
public class ProducerSampleTest {

    private final static String TOPIC_NAME = "hello-topic" ;

    @Test
    public void producer(){
        // 配置
        Properties properties = new Properties() ;
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.BOOTSTRAP_SERVER_ADDRESS) ;
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all") ;
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "0") ;
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "16384") ;
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "1") ;
        properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432") ;
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer") ;
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer") ;
        // Producer 的主对象
        Producer<String, String> producer = new KafkaProducer<>(properties) ;
        // 消息对象
        IntStream.range(0, 100)
                .forEach(index -> {
                    String key = String.format("key-%s", (index + 1));
                    String value = String.format("hello world [%s]", (index + 1));
                    ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, key, value);
                    Future<RecordMetadata> future = producer.send(record);
                    try {
                        RecordMetadata metadata = future.get();
                        log.info("index : {}, partition: {}, offset: {}", (index +1) , metadata.partition(), metadata.offset());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
        // 所有的通道打开都需要关闭
        producer.close();
    }

    @Test
    public void transactionProducer(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,CommonConstants.BOOTSTRAP_SERVER_ADDRESS);
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer") ;
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer") ;
        // 事务配置支持
        properties.put(ProducerConfig.RETRIES_CONFIG, "2") ;
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "hello-transaction-id") ;
        //
        Producer<String, String> producer = new KafkaProducer<>(properties) ;
        producer.initTransactions();
        producer.beginTransaction();
        try {
            for(int index=0; index<10; index++){
                String key = String.format("key-%s", (index + 1));
                String value = String.format("hello world [%s]", (index + 1));
                ProducerRecord<String,String> record = new ProducerRecord<>(TOPIC_NAME, key, value);
                if(index == 8){
                    throw new Exception();
                }
                producer.send(record);
            }
            // 提交事务
            producer.commitTransaction();
        }catch (Exception e){
            //
            log.error("send transaction message error !!", e);
            // 终止事务
            producer.abortTransaction();
        }finally {
            producer.close();
        }
    }


    @Test
    public void transactionProducer2(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.BOOTSTRAP_SERVER_ADDRESS);
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        // 事务支持配置
        properties.put(ProducerConfig.RETRIES_CONFIG, "2");
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "hello-transaction-id");
        // Producer的主对象
        Producer<String,String> producer = new KafkaProducer<>(properties);
        producer.initTransactions();
        producer.beginTransaction();
        try{
            // 消息对象 - ProducerRecoder
            for(int index = 0; index < 10; index ++){
                String key = String.format("key-%s", (index + 1));
                String value = String.format("hello world [%s]", (index + 1));
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, key, value);
                if(index == 8){
                    throw new Exception();
                }
                producer.send(record);
            }
            // 提交事务
            producer.commitTransaction();
        }catch (Exception e){
            e.printStackTrace();
            // 回滚事务
            producer.abortTransaction();
        }finally {
            producer.close();
        }
    }
}
