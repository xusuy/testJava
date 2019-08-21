package com.util;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;


/**
 * @author xsy
 * @create 2019-08-21 17:48
 * @desc
 **/
@AllArgsConstructor
public abstract class FilterRestTemplate implements RestOperations {

    @Delegate//使用restTemplate代理实现所有RestOperations的方法
    protected volatile RestTemplate restTemplate;
}
