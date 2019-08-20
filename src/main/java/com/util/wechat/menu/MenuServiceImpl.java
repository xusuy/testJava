package com.util.wechat.menu;

import com.constant.WechatConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.HttpClientUtils;
import com.util.wechat.AbstractAccessTokenService;
import com.util.wechat.accesstoken.AccessTokenService;
import com.util.wechat.bean.AccessTokenBean;
import com.util.wechat.bean.Menu;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MenuServiceImpl
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2018/9/20 12:46
 * @Version 1.0
 */
@Component
public class MenuServiceImpl extends AbstractAccessTokenService implements MenuService, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
    private static final ObjectMapper MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Autowired
    private AccessTokenService accessTokenService;

    @Override
    protected AccessTokenService getAccessTokenService() {
        return accessTokenService;
    }

    @Override
    public void createMenu(Menu menu) {
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

    @Override
    public String getMenu() {
        return null;
    }

    /**
     * @Description: 直接初始化创建菜单
     * @Author: xiewl
     * @param:
     * @Date: 2018/9/20 15:20
     * @Version 1.0
     */
    @Override
    public void afterPropertiesSet() throws Exception {
//        Menu menus = MessageUtil.getMenu();
//        this.createMenu(menus);
    }

    /**
     * 检查accesstoken是否有效
     */
    protected AccessTokenBean checkAccessToken() {
        AccessTokenBean accessToken = getAccessTokenService().getAccessToken();
        if (accessToken == null) {
            accessToken = getAccessTokenService().refreshAccessToken();
        }
        return accessToken;
    }
}
