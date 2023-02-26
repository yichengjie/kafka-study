package com.yicj.aop.resources;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;


@Slf4j
public class ResourcesTest {

    @Test
    public void classLoaderUrl() throws IOException {
        String resourceName = SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION ;
        ClassLoader classLoader = this.getClass().getClassLoader();
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            UrlResource resource = new UrlResource(url) ;
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            //System.out.println(properties);
            System.out.println(url);
        }
    }

    @Test
    public void classLoaderUrl2() throws IOException {
        String resourceName = SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION ;
        Enumeration<URL> urls = ClassLoader.getSystemResources(resourceName);
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            UrlResource resource = new UrlResource(url) ;
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            System.out.println(url);
        }
    }
}
