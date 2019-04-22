package com.util;

import com.domain.Account;
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

    public static String cookieVal = "";

    /**
     * 带cookies 的 HttpURLConnection get请求
     *
     * @param url_get
     * @param str_param_url
     * @param charset
     * @param cookie
     * @return
     * @throws IOException
     */
    public static String get(String url_get, String str_param_url, String charset, String cookie) throws IOException {
        StringBuilder result = new StringBuilder();
        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
        // String getURL = GET_URL + "?username="  + URLEncoder.encode("fat man", "utf-8");
        String getURL = url_get + "?" + str_param_url;
        URL getUrl = new URL(getURL);
        // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
        // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) getUrl
                .openConnection();
        if (cookie != null) {
            //发送cookie信息上去，以表明自己的身份，否则会被认为没有权限
            System.out.println("set cookieVal = [" + cookie + "]");
            connection.setRequestProperty("Cookie", cookie);
        }
        // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
        // 服务器
        connection.connect();
        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), charset));
        System.out.println("Contents of get request:");
        String lines;
        while ((lines = reader.readLine()) != null) {
            result.append(lines);
        }
        reader.close();
        // 断开连接
        connection.disconnect();
        return result.toString();
    }

    /**
     * 带cookies 的 HttpURLConnection post请求
     *
     * @param url_post
     * @param str_param_body
     * @param charset
     * @param b_flag
     * @param cookies
     * @return
     * @throws IOException
     */
    public static String post(String url_post, String str_param_body, String charset, boolean b_flag, String cookies) throws IOException {
        StringBuilder sb = new StringBuilder();
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(url_post);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl
                .openConnection();
        // Output to the connection. Default is
        // false, set to true because post
        // method must write something to the
        // connection
        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        if ("" != cookies) {
            connection.setRequestProperty("Cookie", cookies);
        }
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // Set the post method. Default is GET
        connection.setRequestMethod("POST");
        // Post cannot use caches
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
        // This method takes effects to
        // every instances of this class.
        // URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
        // connection.setFollowRedirects(true);

        // This methods only
        // takes effacts to this
        // instance.
        // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
        connection.setInstanceFollowRedirects(b_flag);
        // Set the content type to urlencoded,
        // because we will write
        // some URL-encoded content to the
        // connection. Settings above must be set before connect!
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection
                .getOutputStream());
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
        //    String content = "userName=" + URLEncoder.encode("console", "utf-8");
        //    content = content + "&password=" + URLEncoder.encode("12345678", "utf-8");

        System.out.println("http param body = [" + str_param_body + "]");
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
        out.writeBytes(str_param_body);

        out.flush();

        // 取得cookie，相当于记录了身份，供下次访问时使用
        //    cookieVal = connection.getHeaderField("Set-Cookie").split(";")[0]
//        cookieVal = connection.getHeaderField("Set-Cookie");
//        System.out.println("get cookieVal = [" + cookieVal + "]");

        out.close(); // flush and close
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), charset));
        String line;
        System.out.println("Contents of post request:");
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        connection.disconnect();
        return sb.toString();
    }

    /**
     * loginCookie 获取登录cookies
     *
     * @return
     */
    public static String getLoginCookies() {
        // 登入成功 写入cookie
        String encryptString = "loginCookie=eesh+/Oy3GTcrUAk638cZdaQNsabVl/OmUvqMFY9TOG6ytRVv8s5Tis2URB2DQMWXYt0yth4ylc2EiXhPH8l228lwdtaQcAgYaSTkVIxmRLZX3Ue6PUE7lrtk2W5KCzMrh/7jqUh/+Ztx274aGyV3uv2ydIZmeYN1q6bASAlmpUjYYlj8HeS1FTrIbOP0zDI";
        return encryptString;
    }

    public static void main(String[] args) throws IOException {
        String url_post = "https://qxymgr-d.ywsoftware.cn/process/doingTasks";
//        String str_param = "page=1&size=20&start=0&filters[nodeName]=公众号";
//        str_param = URLEncoder.encode(str_param,"utf-8");
        String str_param = "page=1&size=5&start=0&filters" + URLEncoder.encode("[", "utf-8") +
                "nodeName" + URLEncoder.encode("]", "utf-8") + "=" + URLEncoder.encode("公众号", "utf-8");
        System.out.println(str_param);
        String result = get(url_post, str_param, "utf-8", getLoginCookies());
        System.out.println("result====" + result);
    }

}
