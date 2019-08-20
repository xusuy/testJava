package com.util.wechat.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xsy
 * @create 2019-08-20 11:34
 * @desc 微信配置类
 **/
@Data
@Component
public class WechatConfig {
    /**
     *  微信appid
     */
    @Value("${appid}")
    private String appid;
    
    /**
     *  appsecret
     */
    @Value("${appsecret}")
    private String appsecret;
    
    /**
     *  服务端自定义配置的token
     */
    @Value("${token}")
    private String token;

    /**
     *  服务端encodingAESKey
     */
    @Value("${encodingAESKey}")
    private String encodingAESKey;
    
}
