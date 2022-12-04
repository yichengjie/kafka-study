package com.yicj.study.kafka.admin;


import com.yicj.study.kafka.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
public class AdminSampleTest {

    private final static String TOPIC_NAME = "hello-topic" ;


    @Test
    public void initTest(){
        AdminClient adminClient = initAdminClient();
    }

    @Test
    public void createTopic() throws ExecutionException, InterruptedException {
        AdminClient adminClient = initAdminClient();
        NewTopic newTopic = new NewTopic(TOPIC_NAME, 1, Short.parseShort("1"));
        CreateTopicsResult result = adminClient.createTopics(Arrays.asList(newTopic));
        log.info("result : {}", result.all().get());
    }

    @Test
    public void topicList() throws ExecutionException, InterruptedException {
        AdminClient adminClient = initAdminClient();
        //ListTopicsResult result = adminClient.listTopics();
        // 查看内部topic参数
        ListTopicsOptions options = new ListTopicsOptions() ;
        options.listInternal(true) ;
        ListTopicsResult result = adminClient.listTopics(options);
        Set<String> strings = result.names().get();
        strings.stream().forEach(name -> log.info("name : {}", name));
    }

    @Test
    public void describeTopic() throws ExecutionException, InterruptedException {
        AdminClient adminClient = initAdminClient();
        DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(TOPIC_NAME));
        Map<String, TopicDescription> descriptionMap = result.all().get();
        descriptionMap.forEach((name, describe) -> {
            log.info("======>  describe : {}", describe);
        });
        //(name=hello-topic, internal=false,
        //  partitions=
        //      (partition=0, leader=192.168.99.51:9092 (id: 0 rack: null),
        //          replicas=192.168.99.51:9092 (id: 0 rack: null),
        //          isr=192.168.99.51:9092 (id: 0 rack: null)),
        //      (partition=1, leader=192.168.99.51:9092 (id: 0 rack: null),
        //           replicas=192.168.99.51:9092 (id: 0 rack: null),
        //           isr=192.168.99.51:9092 (id: 0 rack: null)))
    }

    /**
     * 增加partition 的数据量
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void incrementPartitions() throws ExecutionException, InterruptedException {
        AdminClient adminClient = initAdminClient();
        Map<String, NewPartitions> newPartitionsMap = new HashMap<>() ;
        NewPartitions newPartitions = NewPartitions.increaseTo(2) ;
        newPartitionsMap.put(TOPIC_NAME, newPartitions) ;
        CreatePartitionsResult result = adminClient.createPartitions(newPartitionsMap);
        result.all().get();
    }

    private AdminClient initAdminClient (){
        Properties properties = new Properties() ;
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.BOOTSTRAP_SERVER_ADDRESS) ;
        AdminClient adminClient = AdminClient.create(properties) ;
        return adminClient ;
    }

}
