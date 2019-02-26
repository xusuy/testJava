package com.util.fileUtil;

import com.util.HttpRequestHelper;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HttpUploadFileUtilTest {

    @Test
    public void doHttpsUploadFiles() {
        try {
            String access_token = "18_9yWAFRIkRkE2_oFGB54HfsQPgWowEjJ1Cbn07P0m_NtiPGpaTDmpg8QI-eVOtjckWS5QnJ1DK54UA0j6hSr1OpbRXGnv1xbwym50BGrOlml4hlwwc26XVx85SUXl_XWII-7vGJ6xEtV5Zw8ZLFEdAGATNM";

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