package com.yicj.study.web;

import com.yicj.study.mvc.HelloMvcApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {})
@SpringBootTest(classes = HelloMvcApplication.class, properties = {})
public abstract class BaseJunitTest {

}
