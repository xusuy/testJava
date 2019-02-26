package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpRequestHelper {

    public static final String CHARSET = "UTF-8";
    private static final CloseableHttpClient HTTP_CLIENT;

    static {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(60000)
                .setSocketTimeout(15000)
                .build();
        HTTP_CLIENT = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    /**
     * HTTP Get 获取内容
     *
     * @param url    请求的url地址 ? 之前的地址
     * @param params 请求的参数
     * @return 页面内容
     */
    public static String sendGet(String url, Map<String, Object> params) {
        if (!StringUtils.hasText(url)) {
            throw new RuntimeException("url can not be null or empty value");
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        String result = null;
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<>(params.size());
                for (String key : params.keySet()) {
                    if (params.get(key) != null) {
                        pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
                    }
                }
                urlBuilder.append("?").append(EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET)));
            }
            HttpGet httpGet = new HttpGet(urlBuilder.toString());
            CloseableHttpResponse response = HTTP_CLIENT.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
            }
            EntityUtils.consume(entity);
            response.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     */
    public static String sendPost(String url, String param) throws IOException {
        StringBuilder result = new StringBuilder();
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
        // 设置通用的请求属性
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//        conn.setRequestProperty("appid", "666666");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8));
        // 发送请求参数
        out.print(param);
        // flush输出流的缓冲
        out.flush();
        // 定义BufferedReader输入流来读取URL的响应
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        out.close();
        bufferedReader.close();
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param targetUrl 发送请求的 URL
     * @param param     请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param encoding  字符编码
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String targetUrl, String param, String encoding, String requestType) throws IOException {
        String type = "";
        if ("1".equals(requestType)) {
            //表单数据(key,value形式)
            type = "application/x-www-form-urlencoded";
        } else if ("2".equals(requestType)) {
            //xml数据
            type = "application/x-javascript text/xml";
        } else if ("3".equals(requestType)) {
            //json数据
            type = "application/json";
        } else if ("5".equals(requestType)) {
            //文件数据
            type = "multipart/form-data";
        }
        String result = "";
        byte[] data = param.getBytes(encoding);
        URL url = new URL(targetUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        //application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
        conn.setRequestProperty("Content-Type", type + "; charset=" + encoding);
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        conn.setConnectTimeout(5 * 1000);
        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();
        // System.out.println(conn.getResponseCode()); //响应代码 200表示成功
        if (conn.getResponseCode() == 200) {
            InputStream inStream = conn.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inStream.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            result = baos.toString(encoding);
            baos.close();
            inStream.close();
        }
        conn.disconnect();
        return result;
    }

    /**
     * @param sendUrl    发送POST方法的请求
     * @param textParams 文字数据
     * @param fileParams 文件数据
     * @return
     */
    public static String sendPostFile(String sendUrl, Map<String, String> textParams,
                                      Map<String, MultipartFile> fileParams) throws Exception {
        StringBuilder result = new StringBuilder();
        String boundary = "--------ywkjRedCat" + MathTimeUtil.getNowDateYMDHMS();

        URL url = new URL(sendUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        //连接超时为10秒
        conn.setConnectTimeout(10000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        conn.connect();

        PrintStream dataOutputStream = new PrintStream(conn.getOutputStream());
        //文件数据
        Set<String> keySetFile = fileParams.keySet();
        for (String name : keySetFile) {
            MultipartFile file = fileParams.get(name);
            dataOutputStream.print("--" + boundary + "\r\n");
            dataOutputStream.print("Content-Disposition: form-data; name=\"" + name
                    + "\"; filename=\"" + encode(file.getOriginalFilename()) + "\"\r\n");
            dataOutputStream.print("\r\n");
            dataOutputStream.write(getBytes(file));
            dataOutputStream.print("\r\n");
        }
        //文字数据
        Set<String> keySetText = textParams.keySet();
        keySetText.forEach(name -> {
            String value = textParams.get(name);
            dataOutputStream.print("--" + boundary + "\r\n");
            dataOutputStream.print("Content-Disposition: form-data; name=\"" + name + "\"\r\n");
            dataOutputStream.print("\r\n");
            dataOutputStream.print(value + "\r\n");
        });
        //结尾数据
        dataOutputStream.print("--" + boundary + "--" + "\r\n");
        dataOutputStream.print("\r\n");
        dataOutputStream.flush();
        dataOutputStream.close();
        InputStream inStream;
        if (conn.getResponseCode() >= 400) {
            inStream = conn.getErrorStream();
        } else {
            inStream = conn.getInputStream();
        }
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inStream, StandardCharsets.UTF_8));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        inStream.close();
        conn.disconnect();
        return result.toString();
    }

    /**
     * 对包含中文的字符串进行转码，此为UTF-8。
     */
    private static String encode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8");
    }

    /**
     * 把文件转换成字节数组
     */
    private static byte[] getBytes(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int index;
        while ((index = inputStream.read(bytes)) != -1) {
            out.write(bytes, 0, index);
        }
        inputStream.close();
        return out.toByteArray();
    }
}
