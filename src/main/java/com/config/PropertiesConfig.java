package com.config;

import com.util.IniProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author xsy
 * @create 2019-03-11 9:54
 * @desc 属性配置
 **/
@Configuration
public class PropertiesConfig {

    @Bean
    public IniProperties loadIniProperties() throws IOException {
        return IniProperties.getIniPropertiesInstance();
    }
}
