package com.yicj.study.kafka.utils;

import java.util.UUID;

public class IdGen {

    public static String uuid(){
        return UUID.randomUUID().toString() ;
    }
}
