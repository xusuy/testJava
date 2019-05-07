package com.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author xsy
 * @create 2019-05-05 17:52
 * @desc
 **/
public class WxApiUtil {

    @Test
    public void getwxacodeTest() throws IOException {
        String url = "https://api.weixin.qq.com/wxa/getwxacode?access_token=21_Rv9CDMzw1zpRiEozZJfBxBAvJs6DdLccbYd7WlNp52QB4M0RHEz85SaCyZbFVG_jPeKDgqTACbgi8JezcL4roH52Ei5J82qDnopAZLrke6NrgjnTavUia2utAOFOSRRm0m_yYwAwWkmJ6jMVAASgAEALLD";
        String param = "{\"path\":\"pages/index/index\"}";
        ConnectionUtil.sendPostCreateFile(url, param, "utf-8", "3", null);
    }
}
