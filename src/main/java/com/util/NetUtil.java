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
        System.out.println(URLEncoder.encode("杨五英", "utf-8"));
        System.out.println(URLDecoder.decode("%E7%AB%8B%E6%A1%88%E4%BF%A1%E6%81%AF%E9%87%87%E9%9B%86%E8%A1%A8", "utf-8"));
        System.out.println(URLDecoder.decode("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx38a85f50606305b0&redirect_uri=https%3A%2F%2Fweixin-proxy.ywsoftware.com%2Fbefree-weixin%2Findex%2F1&response_type=code&scope=snsapi_userinfo&state=123&uin=MjIzNDU2ODgxMw%3D%3D&key=0c76559792b26a243c2c267ef50e8e1a13086d5bc6072eab816b969b2a3dfe9a9e42e3583aae4eeb35c266eb5e063114&pass_ticket=IiV1/JP0m+isFQz5M/VSa0XjRkv+sp4cTI+ZvhwTtA7W+oMuKUKPYAjOV65mNLl/VmbaUGLXlg/llP+PmdKaZg==", "utf-8"));
        System.out.println(URLDecoder.decode("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6f013ee35dea110f&redirect_uri=https%3A%2F%2Fweixin-proxy.ywsoftware.com%2Fqxy-weixin%2Flogin&response_type=code&scope=snsapi_userinfo&state=123&uin=MjIzNDU2ODgxMw%3D%3D&key=8539455cfcf7130c19c02ac3ba5599f900b1b8b02f5ad5141af2bbe87da462d777d4a1833bd9679193b4ebf77091ee0f&pass_ticket=IiV1/JP0m+isFQz5M/VSa0XjRkv+sp4cTI+ZvhwTtA7W+oMuKUKPYAjOV65mNLl/VmbaUGLXlg/llP+PmdKaZg==", "utf-8"));
    }
}
