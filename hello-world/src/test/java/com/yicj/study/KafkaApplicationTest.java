package com.yicj.study;


import com.yicj.study.producer.KafkaProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class KafkaApplicationTest {

    @Autowired
    private KafkaProducerService producerService ;

    @Test
    public void testSendMessageSync() throws Exception {
        String topic = "hello-kafka-test-topic" ;
        String key = "key1" ;
        String message = "first Message" ;
        producerService.sendMessageSync(topic, key, message);
    }
}
