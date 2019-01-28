package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author xsy
 * @create 2019-01-18 14:17
 * @desc java 模拟curl
 **/
public class CURLTest {
    public static void main(String[] args) {
        String access_token = "17_yoggylxeLVCKl8CqdqudS6RifF4oPYhBdqG-o8DQkNG7-vxbQBhHLGg9chldOV6GQlEur-OGhzQBg2CEqOfaFYKH-_LLctkYh3COgX9qppQ4oAah6LCbLCv4f9IHfDUrGNcQdWK3ige-IIYbONObAGADCD";
//        String []cmds = {"curl", "-i", "-w", "状态%{http_code}；DNS时间%{time_namelookup}；"
//                + "等待时间%{time_pretransfer}TCP 连接%{time_connect}；发出请求%{time_starttransfer}；"
//                + "总时间%{time_total}","http://www.baidu.com"};
        String[] cmds = {"curl", "–F buffer=@D:\\work\\anxincarapi\\微信卡券\\素材\\20190118142538.png", "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + access_token};
        ProcessBuilder pb = new ProcessBuilder(cmds);
        pb.redirectErrorStream(true);
        Process p;
        try {
            p = pb.start();
            BufferedReader br = null;
            String line = null;

            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = br.readLine()) != null) {
                System.out.println("\t" + line);
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
