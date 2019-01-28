package com.util;

import com.domain.TemplateMsg;

import java.util.TreeMap;

/**
 * @author xsy
 * @create 2018-10-27 14:57
 * @desc 微信推送信息工具类
 **/
public class WxPushMessageUtil {

    /**
     * 发送微信模板消息，消息体组装
     *
     * @param template_id 模板id
     * @param firstData
     * @param keyword1
     * @param keyword2
     * @param keyword3
     * @param keyword4
     * @param remark
     * @param touser      发送者的openid
     * @param targetUrl   跳转url,无传""
     * @return
     * @throws Exception
     */
    public static TemplateMsg getWeChatTemplateMsgModel(String template_id, String firstData, String keyword1,
                                                        String keyword2, String keyword3, String keyword4,
                                                        String remark, String touser, String targetUrl) throws Exception {
        // 组装模板参数
        TreeMap<String, TreeMap<String, String>> params = new TreeMap<>();

        //根据具体模板参数组装
        params.put("first", TemplateMsg.item(firstData, "#000000"));
        params.put("keyword1", TemplateMsg.item(keyword1, "#000000"));
        params.put("keyword2", TemplateMsg.item(keyword2, "#000000"));
        params.put("keyword3", TemplateMsg.item(keyword3, "#000000"));
        params.put("keyword4", TemplateMsg.item(keyword4, "#000000"));
        params.put("remark", TemplateMsg.item(remark, "#000000"));
        TemplateMsg templateMsg = new TemplateMsg();
        // 设定模板key
        templateMsg.setTemplate_id(template_id);
        // 指定发送者
        templateMsg.setTouser(touser);
        templateMsg.setUrl(targetUrl);
        templateMsg.setData(params);
        // 发送模板消息
        return templateMsg;
    }
}
