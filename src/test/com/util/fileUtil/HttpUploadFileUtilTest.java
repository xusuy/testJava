package com.util.fileUtil;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class HttpUploadFileUtilTest {

    @Test
    public void doHttpsUploadFiles() {
        try {
            String access_token = "17_yOuGMCjPz2Ew1qtD-EbjC3yTbwRDlux2UBt-d6Ev3lH9EJ2OjFp-93UZQZulxLIIVnpMTP0zng62jHkpdAaXpkPxHpBkbu7oQqbrd5mhdS_frzjRWkuyHz5GcDKmazjaDFVT-khJkTboOMkPQSXcAAAZBG";

            File file1 = new File("D:\\work\\anxincarapi\\微信卡券\\素材\\20190118151511.png");
            String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + access_token;
            ArrayList<File> fileList = new ArrayList<>();
            fileList.add(file1);
//            System.out.println(doHttpUploadFiles(url,fileList,null));
            System.out.println(HttpUploadFileUtil.doHttpsUploadFiles(fileList, url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}