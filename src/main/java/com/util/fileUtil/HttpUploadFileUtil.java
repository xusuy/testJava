package com.util.fileUtil;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xsy
 * @create 2018-08-08 19:51
 * @desc http请求相关
 **/
public class HttpUploadFileUtil {

    /**
     * 客户端多文件上传给远程服务器
     *
     * @param url       远程服务器
     * @param files     多文件集合
     * @param mapParams
     */
    public static String doHttpUploadFiles(String url, List<File> files, HashMap<String, String> mapParams) {
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "utf-8");
        try {
            MultipartEntity entity = new MultipartEntity();//多个表单对象
            for (File file : files) {
                ContentBody fileBody = new FileBody(file); //表单文件域
                entity.addPart("file", fileBody);
            }
//            entity.addPart("userName", new StringBody(mapParams.get("userName")));  // 字符参数部分
            httpPost.setEntity(entity);
            HttpResponse response = client.execute(httpPost);//执行post操作，并返回response
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 上传至https服务器
     *
     * @param files
     * @param url
     * @return
     */
    public static String doHttpsUploadFiles(List<File> files, String url) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;

        // 把文件转换成流对象FileBody
        MultipartEntity entity = new MultipartEntity();//多个表单对象
        for (File file : files) {
            ContentBody fileBody = new FileBody(file); //表单文件域
            entity.addPart("file", fileBody);
        }
        try {
            //***************注意这里的代码******
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //封装其他参数到Stringbody（需要把int转成String再放入）
//            StringBody username = new StringBody("张三");
//            StringBody password = new StringBody("123456");
//            StringBody type1 = new StringBody(String.valueOf(type));//type为int
            //参数放入请求实体（包括文件和其他参数）
//            reqEntity.addPart("type", type1);
//            reqEntity.addPart("username", username);
//            reqEntity.addPart("password", password);
            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            File file1 = new File("d://testImage/3.jpg");
            File file2 = new File("d://testImage/04.png");
            String url = "https://jxhmgr-d.ywsoftware.cn/hgylUpload/uploadFiles";
            ArrayList<File> fileList = new ArrayList<>();
            fileList.add(file1);
            fileList.add(file2);
//            System.out.println(doHttpUploadFiles(url,fileList,null));
            System.out.println(doHttpsUploadFiles(fileList, url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
