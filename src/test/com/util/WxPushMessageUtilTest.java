package com.util;

import com.domain.TemplateMsg;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class WxPushMessageUtilTest {
    private static final Logger logger = LoggerFactory.getLogger(WxPushMessageUtilTest.class);

    @Test
    public void test() throws Exception {
        String openid = "oPML11Qf8EfSCZry7qKFmUYZ-1vM";
        String template_id = "58OVR6HAqX6IYyDGorYFeAOq3Qyy0T1dw9cS8LAaUOQ";
        String firstData = "您有一个新预约待处理";
        String keyword1 = MathTimeUtil.getYYRSMForDate(new Date());
        String keyword2 = "测试人员";
        String keyword3 = "18146616666";
        String keyword4 = "中医养生指导";
        String remark = "请准时接待";
        TemplateMsg templateMsg = WxPushMessageUtil.getWeChatTemplateMsgModel(template_id, firstData, keyword1, keyword2, keyword3,
                keyword4, remark, openid, "");
        String accessToken = "15_-TpOhxEkOFBbn8ZMjEawNha9uVhuslthzUZ8_In7eKq7XYlY269GAxnDdl3rvVeYCQKbSdaf-P1FqYvjDOCy2O4ZWkzYwbjrZxUK1aygQl7J_qYa9X4YWqUq_XU3lzVcFZ4hhk2Y1_Mt6A9_EOFdAHAJTK";
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", accessToken);
        String paramJson = JacksonJsonUtil.toJSon(templateMsg);
        String postResponse = HttpClientUtils.sendPost(url, paramJson);
        if (StringUtils.isNotEmpty(postResponse) && postResponse.indexOf("\"ok\"") > 0) {
            logger.info(postResponse);
        } else {
            logger.info("微信发送模板消息失败,url=" + url + "；请求参数=" + paramJson + "；返回数据=" + postResponse);
        }
    }
}