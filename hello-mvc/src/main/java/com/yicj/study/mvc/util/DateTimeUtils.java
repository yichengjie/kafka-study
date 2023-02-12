package com.yicj.study.mvc.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class DateTimeUtils {

    public static long toEpochMilli(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
