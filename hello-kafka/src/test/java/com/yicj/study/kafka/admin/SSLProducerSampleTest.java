package com.yicj.study.kafka.admin;

import com.yicj.study.kafka.constants.CommonConstants;
import com.yicj.study.kafka.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.junit.Test;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Slf4j
public class SSLProducerSampleTest {

    private final static String TOPIC_NAME = "hello-topic" ;

    @Test
    public void producer(){
        // 配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.99.51:8989");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,"0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");

        properties.put("security.protocol","SSL");
        properties.put("ssl.endpoint.identification.algorithm","");
        File file = CommonUtils.copyClassPathTempFile(
                "client.truststore.jks", "client.truststore.jks");
        properties.put("ssl.truststore.location",file.getPath());
        properties.put("ssl.truststore.password","hello123");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
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

}
