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
        Producer<String, String> producer = this.initProducer();
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

    private Producer<String, String> initProducer(){
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
        return producer ;
    }

}
