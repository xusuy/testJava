package com.util.wechat;

import com.constant.WechatConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.HttpClientUtils;
import com.util.wechat.accesstoken.AccessTokenService;
import com.util.wechat.bean.AccessTokenBean;
import com.util.wechat.bean.Button;
import com.util.wechat.bean.ComplexButton;
import com.util.wechat.bean.Menu;
import com.util.wechat.bean.ViewButton;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author xsy
 * @create 2019-08-20 14:23
 * @desc 微信测试
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-spring.xml"})
public class WechatTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatTest.class);
    private static final ObjectMapper MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Resource
    private AccessTokenService accessTokenService;

    @Test
    public void createMenu() throws Exception {
        Menu menus = getMenu();
        createMenu(menus);
    }

    public void createMenu(Menu menu) throws Exception {
        Boolean isTrue;
        AccessTokenBean accessToken = checkAccessToken();
        try {
            String url = WechatConstant.CREATE_MENU_URL.replaceAll(WechatConstant.ACCESS_TOKEN, accessToken.getAccess_token());
            String paramJson = MAPPER.writeValueAsString(menu);
            String postResponse = HttpClientUtils.sendPost(url, paramJson);
            if (StringUtils.isNotEmpty(postResponse) && postResponse.indexOf("ok") > 0) {
                isTrue = true;
            } else {
                LOGGER.error(postResponse);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 检查accesstoken是否有效
     */
    protected AccessTokenBean checkAccessToken() throws Exception {
        String access_token_url = WechatConstant.ACCESS_TOKEN_FETCH_URL.replaceAll("APPID", "wx6a5237acedbd681a").replaceAll("APPSECRET", "a9066e47f3ad2ed9fdfbfad01254c83b");
        String content = null;
        AccessTokenBean accessTokenBean;
        content = HttpClientUtils.sendGet(access_token_url);
        accessTokenBean = MAPPER.readValue(content, AccessTokenBean.class);
        if (accessTokenBean == null) {
            accessTokenBean = accessTokenService.refreshAccessToken();
        }
        return accessTokenBean;
    }

    public Menu getMenu() {
        ViewButton btn11 = new ViewButton();
        btn11.setName("车辆估价");
        btn11.setType("view");
        btn11.setUrl("http://ygst.ywsoftware.com/smc/Evaluation.html");

        ViewButton btn12 = new ViewButton();
        btn12.setName("车型查询");
        btn12.setType("view");
        btn12.setUrl("http://ygst.ywsoftware.com/smc/FindVehicle.html");

        ViewButton btn21 = new ViewButton();
        btn21.setName("关于我们");
        btn21.setType("view");
        btn21.setUrl("http://m.semache.com/web/AboutUs.html");

        ViewButton btn22 = new ViewButton();
        btn22.setName("账号绑定");
        btn22.setType("view");
        btn22.setUrl("https://oa-devapp.ywsoftware.cn/web-static/html/wechat/wxIndex.html?type=1");


//        CommonButton btn31 = new CommonButton();
//        btn31.setName("A1");
//        btn31.setType("click");
//        btn31.setKey("31");
//
//        CommonButton btn33 = new CommonButton();
//        btn33.setName("A2");
//        btn33.setType("click");
//        btn33.setKey("33");
//
//        CommonButton btn34 = new CommonButton();
//        btn34.setName("A3");
//        btn34.setType("click");
//        btn34.setKey("34");
//
//        CommonButton btn35 = new CommonButton();
//        btn35.setName("A4");
//        btn35.setType("click");
//        btn35.setKey("35");


        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("二手车");
        mainBtn1.setSub_button(new Button[]{btn11, btn12});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("个人中心");
        mainBtn2.setSub_button(new Button[]{btn22, btn21});


//        ComplexButton mainBtn3 = new ComplexButton();
//        mainBtn3.setName("更多");
//        mainBtn3.setSub_button(new Button[]{btn31, btn33, btn34, btn35});

        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2});

        return menu;
    }
}
