package com.util;

import java.net.InetAddress;
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

}
