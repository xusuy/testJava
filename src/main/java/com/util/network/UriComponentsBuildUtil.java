package com.util.network;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;

/**
 * @author xsy
 * @create 2019-09-17 16:13
 * @desc UriComponentsBuilder 工具测试：uri的构建工具
 **/
public class UriComponentsBuildUtil {
    public static void main(String[] args) {
        UriComponents build1 = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("github.com")
                .path("/search")
                .queryParam("q", "springboot")
                .build();
        System.out.println(build1.toString());

        try {
            build1.encode("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(build1.toString());

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("www.test2.com")
                .path("/{template}")
                .queryParam("query", "a")
                .build().expand("templatePath");
        System.out.println(uriComponents.toString()); // http://www.test2.com/templatePath?query=a
    }
}
