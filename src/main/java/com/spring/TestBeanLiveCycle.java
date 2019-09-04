package com.spring;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * spring bean 的生命周期
 * 推荐使用@PostConstruct和@PreDestroy，来自JSR250,对Spring框架0耦合
 */
@Data
public class TestBeanLiveCycle implements InitializingBean, DisposableBean {
    private final Logger logger = LoggerFactory.getLogger(TestBeanLiveCycle.class);
    private String message;

    public TestBeanLiveCycle() {
        logger.info("step 1 this is in contruct, message=" + message);
    }

    @PostConstruct
    public void postConstruct() {
        logger.info("step 2 in  bean's @PostConstruct, message=" + getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("step 3 bean is in InitializingBean afterPropertiesSet(), message=" + getMessage());
    }

    public void xmlCustomInit() {
        logger.info("step 4 bean is in xml config custom init, message=" + message);
    }

    @PreDestroy
    public void preDestroy() {
        logger.info("step 5 in bean's @PreDestroy, message=" + getMessage());
    }

    @Override
    public void destroy() throws Exception {
        //自定义的销毁方法没做销毁，所以TestBeanLiveCycle bean还存在
        logger.info("step 6 bean is in DisposableBean destroy(), message=" + getMessage());
    }

    public void xmlCustomDestryoy() {
        logger.info("step 7 bean is in xml config custom destroy, message=" + getMessage());
    }
}
