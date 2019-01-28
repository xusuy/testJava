package com.constant;

/**
 * 放置微信中的一些常量
 */
public class WechatConstant {


    /**
     * 获取access_token的url
     */
    public static final String ACCESS_TOKEN_FETCH_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取用户基本信息的url
     */
    public static final String WECHAT_USER_FETCH_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=LANG";

    /**
     * 批量获取用户基本信息的url
     */
    public static final String WECHAT_USER_BATCH_FETCH_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

    /**
     * 默认编码
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * url中的accessToken占位符
     */
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    /**
     * 发送模板消息
     */
    public static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    /**
     * 菜单创建（POST） 限100（次/天）
     */
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /**
     * 查询菜单数据
     */
    public static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    /**
     * 删除菜单
     */
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

}
