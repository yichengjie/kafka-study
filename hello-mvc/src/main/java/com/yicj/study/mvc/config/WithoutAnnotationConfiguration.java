package com.yicj.study.mvc.config;


import com.yicj.study.mvc.service.HelloService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;

/**
 * SpringApplication application = new SpringApplication(HelloMvcApplication.class);
 * // 配置源配置
 * Set<String> sources = new HashSet<>() ;
 * sources.add(WithoutAnnotationConfiguration.class.getName()) ;
 * application.setSources(sources);
 * application.setSources(sources);
 */
@Slf4j
@Data
@ConditionalOnWebApplication
//@ConditionalOnBean
public class WithoutAnnotationConfiguration {

    @Autowired
    private ObjectProvider<HelloService> objectProvider ;

    @Value("${admin.name}")
    private String name ;

    @Value("${admin.age}")
    private Integer age ;

    public WithoutAnnotationConfiguration(){
        log.info("WithoutAnnotationConfiguration 对象被创建");
    }

}
