package com.yicj.study.mvc.listener;

import com.yicj.study.mvc.listener.event.RegisterSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author: yicj
 * @date: 2023/4/10 22:06
 */
@Slf4j
@Component
public class UserRegisterEventListener {

    @EventListener
    public void listener(RegisterSuccessEvent event){
        log.info("----------------------------------");
        log.info("user register success .... {}", event);
        log.info("----------------------------------");
    }
}
