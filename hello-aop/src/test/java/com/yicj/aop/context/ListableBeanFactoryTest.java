package com.yicj.aop.context;

import com.yicj.aop.beans.Dog;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;

public class ListableBeanFactoryTest {

    @Test
    public void list(){
        ClassPathResource resource = new ClassPathResource("listable-container.xml") ;
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory) ;
        beanDefinitionReader.loadBeanDefinitions(resource) ;
        // 1. 打印容器中所有bean
        System.out.println("加载xml文件后容器中的bean如下: ");
        Arrays.stream(beanFactory.getBeanDefinitionNames())
                .forEach(System.out::println);
        // 2. 手动注册一个单例bean
        beanFactory.registerSingleton("doggg", new Dog());
        // 3. 打印容器中所有的bean
        System.out.println("手动注册单例bean后容器中所有bean如下: ");
        Arrays.stream(beanFactory.getBeanDefinitionNames())
                .forEach(System.out::println);
        // 4. 通过registerBeanDefinition 手动注入
        beanFactory.registerBeanDefinition("doggg2", new RootBeanDefinition(Dog.class));
        // 再次打印
        System.out.println("通过registerBeanDefinition注册后容器中所有bean如下: ");
        Arrays.stream(beanFactory.getBeanDefinitionNames())
                .forEach(System.out::println);
    }
}
