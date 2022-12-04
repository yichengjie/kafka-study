package com.yicj.study.kafka.admin;


import com.yicj.study.kafka.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
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

    private AdminClient initAdminClient (){
        Properties properties = new Properties() ;
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.BOOTSTRAP_SERVER_ADDRESS) ;
        AdminClient adminClient = AdminClient.create(properties) ;
        return adminClient ;
    }

}
