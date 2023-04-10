package com.yicj.study.web.listener;

import com.yicj.study.mvc.listener.event.RegisterSuccessEvent;
import com.yicj.study.web.BaseJunitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author: yicj
 * @date: 2023/4/10 22:10
 */
@Slf4j
public class UserRegisterEventListenerTest extends BaseJunitTest {

    @Autowired
    private ApplicationContext context ;

    @Test
    public void registerSuccess(){
        context.publishEvent(new RegisterSuccessEvent("张三","BJS"));
    }

}
