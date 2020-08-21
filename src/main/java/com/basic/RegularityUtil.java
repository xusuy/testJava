package com.basic;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author： xusy
 * @Date 2020/7/17 11:33
 * @Version 1.0
 * @Describe 正则相关
 */
public class RegularityUtil {
    public static void main(String[] args) {
        String html = "<div class=\"abstract\">\n" +
                " 摘要\n" +
                "</div> \n" +
                "<div class=\"b-review\">\n" +
                " 【橡胶主产国受真菌病袭击 产量或减半 胶价能否逆袭？】在印度尼西亚和马来西亚橡胶种植园接连遭遇Pestalotiopsis真菌病袭击后，本周一，据泰国橡胶管理局称，泰国主要橡胶种植区也受到真菌病暴发的袭击，可能导致这一区域的产量减半。泰国橡胶管理局表示，这种真菌疾病最近在泰国南部一个重要橡胶产区那拉提瓦府(Narathiwat)的三个地区被发现。（期货日报）\n" +
                "</div> ";

        System.out.println(getElementTextByClassName(html, "b-review"));
        String startStr = "<div class=\"b-review\">\n";
        int length = startStr.length();
        int startIndex = html.indexOf("<div class=\"b-review\">\n");
        int endIndexOf = html.indexOf("</div>", startIndex);
        System.out.println(html.substring(startIndex + length, endIndexOf));
    }

    public static String getElementTextByClassName(String html, String className) {
        Pattern pattern = Pattern.compile("<div class=\"b-review\">(.?)</div>");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
