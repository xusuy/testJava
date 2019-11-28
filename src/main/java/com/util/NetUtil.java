package com.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;

/**
 * @author xsy
 * @create 2018-12-28 8:39
 * @desc 网络相关工具类
 **/
public class NetUtil {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }

    @Test
    public void test1() throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("杨五英","utf-8"));
        System.out.println(URLDecoder.decode("%E7%AB%8B%E6%A1%88%E4%BF%A1%E6%81%AF%E9%87%87%E9%9B%86%E8%A1%A8","utf-8"));
    }
}
