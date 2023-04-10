package com.yicj.study.mvc.listener.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: yicj
 * @date: 2023/4/10 22:07
 */
@Data
@AllArgsConstructor
public class RegisterSuccessEvent implements Serializable {

    private String username ;

    private String address ;
}
