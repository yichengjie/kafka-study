package com.yicj.study.web.boot;

import com.yicj.study.web.BaseJunitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Slf4j
public class DefaultApplicationArgumentsTest{

    //@Autowired
    //private ApplicationArguments springApplicationArguments ;


    @Test
    public void getOptionNames(){
        String [] args = {"--foo=bar", "--debug=true"} ;
        ApplicationArguments springApplicationArguments = new DefaultApplicationArguments(args) ;
        Set<String> optionNames = springApplicationArguments.getOptionNames();
        log.info("----> optionNames : {}", optionNames);
    }

    @Test
    public void getOptionValues(){
        String [] args = {"--foo=bar", "--debug=true", "name=张三"} ;
        ApplicationArguments springApplicationArguments = new DefaultApplicationArguments(args) ;
        Set<String> optionNames = springApplicationArguments.getOptionNames();
        Consumer<String> consumer = name ->
                log.info("name : {} <---> value : {}", name, springApplicationArguments.getOptionValues(name)) ;
        optionNames.forEach(consumer);
    }

    @Test
    public void getNonOptionArgs(){
        //String [] args = {"--foo=bar", "--debug=true", "-Dname=张三"} ;
        String[] args = {"--a", "--b=bb","--c=a,b,c","--c=d", "name=true"};
        ApplicationArguments springApplicationArguments = new DefaultApplicationArguments(args) ;
        List<String> nonOptionArgs = springApplicationArguments.getNonOptionArgs();
        log.info("---> {}", nonOptionArgs);
//        SimpleCommandLinePropertySource ps = new SimpleCommandLinePropertySource(args);
//        log.info(ps.getProperty("b"));
//        log.info(ps.getProperty("c"));

    }
}
