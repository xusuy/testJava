package com.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@ContextConfiguration("classpath:spring-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringElTest {
    @Resource
    private TestBeanLiveCycle testBeanLiveCycle;

    private String strValue = "12";
    @Value("#{T(Math).PI}")
    private double pi;

    @Value("#{T(Math).random()}")
    private double random;

    @Value("#{1+2}")
    private int intAdd;

    @Value("#{'1'+'2'}")
    private String stringAdd;

    @Value("#{testBeanLiveCycle.message eq 'hello'}")//动态参数解析
    private boolean equalString;

    @Value("#{testBeanLiveCycle.name?: 'hello888'}")//动态参数解析:为null时返回
    private String defaultStringValue;

    @Test
    public void testValue() {
        System.out.println("PI==="+pi);
        System.out.println("random==="+random);
        System.out.println("intAdd==="+intAdd);
        System.out.println("stringAdd==="+stringAdd);
        System.out.println("equalString==="+equalString);
        System.out.println("defaultStringValue==="+defaultStringValue);
    }
}