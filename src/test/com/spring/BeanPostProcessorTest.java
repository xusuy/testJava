package com.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:spring-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BeanPostProcessorTest {

    @Autowired
    private AbstractApplicationContext applicationContext;

    @Test
    public void testBeanPostProcessor() {
        TestBeanLiveCycle testBeanLiveCycle = (TestBeanLiveCycle) applicationContext.getBean("testBeanLiveCycle");
        System.out.println(testBeanLiveCycle);
    }
}