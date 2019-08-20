package com.util.wechat;

import com.util.wechat.accesstoken.AccessTokenService;
import com.util.wechat.bean.AccessTokenBean;
import org.junit.platform.commons.util.Preconditions;

/**
 * @author xsy
 * @create 2019-08-20 11:28
 * @desc 所有微信接口都应该继承该类来获得AccessTokenService对象及检验accessToken是否有效的方法
 **/
public abstract class AbstractAccessTokenService {

    protected abstract AccessTokenService getAccessTokenService();

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
