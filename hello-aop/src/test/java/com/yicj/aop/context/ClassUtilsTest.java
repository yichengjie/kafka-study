package com.yicj.aop.context;

import com.yicj.aop.HelloAopApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.ClassUtils;


@Slf4j
public class ClassUtilsTest {

    @Test
    public void getPackageName(){
        StandardAnnotationMetadata metadata =  new StandardAnnotationMetadata(HelloAopApplication.class, true);;
        String packageName = ClassUtils.getPackageName(metadata.getClassName());
        log.info("package name : {}", packageName);
    }

    @Test
    public void getPackageName2(){
        StandardAnnotationMetadata metadata =  new StandardAnnotationMetadata(StandardAnnotationMetadataTest.Anno.class, true);
        String packageName = ClassUtils.getPackageName(metadata.getClassName());
        log.info("package name : {}", packageName);
    }

}
