package com.frame.config;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader extends PropertyPlaceholderConfigurer {

    private Properties props;

    @Override
    protected void loadProperties(Properties props) throws IOException {
        // 添加 env 前缀的环境变量到属性中；
        System.getenv().forEach((key, value) -> {
            if (key.startsWith("env")) {
                props.put(key, value);
            }
        });

        super.loadProperties(props);
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        super.processProperties(beanFactoryToProcess, props);

        this.props = props;

        // 临时解决不能用配置注入 log4j 的方式;
        PropertyConfigurator.configure(props);
    }

    public String getProperty(String key) {
        return this.props.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return this.props.getProperty(key, defaultValue);
    }

    public Object setProperty(String key, String value) {
        return this.props.setProperty(key, value);
    }
}