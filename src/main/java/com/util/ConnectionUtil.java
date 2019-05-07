package com.util;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ConnectionUtil {
    /**
     * 向指定 URL 发送POST方法的请求,将返回的文件流，创建图片
     *
     * @param targetUrl 发送请求的 URL
     * @param param     请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param encoding  字符编码
     * @return 所代表远程资源的响应结果
     */
    public static int sendPostCreateFile(String targetUrl, String param, String encoding, String requestType, String cookies) {
        if ("1".equals(requestType)) {
            requestType = "application/x-www-form-urlencoded";//表单数据(key,value形式)
        } else if ("2".equals(requestType)) {
            requestType = "application/x-javascript text/xml";//xml数据
        } else if ("3".equals(requestType)) {
            requestType = "application/x-javascript";//json数据
        } else if ("5".equals(requestType)) {
            requestType = "multipart/form-data";//文件数据
        }
        try {
            byte[] data = param.getBytes(encoding);
            URL url = new URL(targetUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (StringUtils.isNotEmpty(cookies)) {
                conn.setRequestProperty("Cookie", cookies);
            }
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            //application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            conn.setRequestProperty("Content-Type", requestType + "; charset=" + encoding);
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            conn.setConnectTimeout(5 * 1000);
            OutputStream outStream = conn.getOutputStream();
            outStream.write(data);
            outStream.flush();
            outStream.close();
//		   System.out.println(conn.getResponseCode()); //响应代码 200表示成功
            if (conn.getResponseCode() == 200) {
                FileOutputStream outFile = new FileOutputStream("D:\\wxacode\\code.png");
                InputStream inStream = conn.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = inStream.read(buf)) != -1) {
                    outFile.write(buf, 0, len);
                }
                outFile.close();
                inStream.close();
                return 1;
            } else {
                InputStream errorStream = conn.getErrorStream();
                int len = 0;
                byte[] buf = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((len = errorStream.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                conn.disconnect();
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 向指定 URL 发送Get方法的请求
     *
     * @param url     发送请求的 完整URL
     * @param charset 设置读取返回内容的字符编码
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String charset, String cookies) {
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            if (StringUtils.isNotEmpty(cookies)) {
                //发送cookie信息上去，以表明自己的身份，否则会被认为没有权限
                System.out.println("set cookieVal = [" + cookies + "]");
                conn.setRequestProperty("Cookie", cookies);
            }
            int resultCode = conn.getResponseCode();
            if (resultCode == 200) {
                InputStream in = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = in.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                result = baos.toString(charset);
                baos.close();
                in.close();
            }
            conn.disconnect();
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * @param sendUrl    发送POST方法的请求
     * @param textParams 文字数据Map<String, String>
     * @param fileparams 文件数据Map<String,File>
     * @param encoding   访问编码"utf-8"
     * @return
     * @author zhaofh 2016年10月9日
     */
    public static String sendPostFile(String sendUrl, Map<String, String> textParams, Map<String, File> fileparams, String encoding) {
        String result = "";
        try {
            String boundary = "--------ywkjOneCarAPI" + MathTimeUtil.dateParseStringForYMDHMSS(new Date());

            URL url = new URL(sendUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(10000); //连接超时为10秒
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);
            conn.connect();

            DataOutputStream ds = new DataOutputStream(conn.getOutputStream());

            //文件数据
            Set<String> keySet = fileparams.keySet();
            for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
                String name = it.next();
                File value = fileparams.get(name);
                ds.writeBytes("--" + boundary + "\r\n");
                ds.writeBytes("Content-Disposition: form-data; name=\"" + name
                        + "\"; filename=\"" + encode(value.getName()) + "\"\r\n");
                ds.writeBytes("Content-Type: " + getContentType(value) + "\r\n");
                ds.writeBytes("\r\n");
                ds.write(getBytes(value));
                ds.writeBytes("\r\n");
            }
            //文字数据
            Set<String> keySet_text = textParams.keySet();
            for (Iterator<String> it = keySet_text.iterator(); it.hasNext(); ) {
                String name = it.next();
                String value = textParams.get(name);
                ds.writeBytes("--" + boundary + "\r\n");
                ds.writeBytes("Content-Disposition: form-data; name=\"" + name
                        + "\"\r\n");
                ds.writeBytes("\r\n");
                ds.writeBytes(encode(value) + "\r\n");
            }
            //结尾数据
            ds.writeBytes("--" + boundary + "--" + "\r\n");
            ds.writeBytes("\r\n");
            ds.flush();
            ds.close();

            if (conn.getResponseCode() == 200) {
                InputStream inStream = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = inStream.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                result = baos.toString("utf-8");
                baos.close();
                inStream.close();
            }
            conn.disconnect();
        } catch (Exception e) {
        }
        return result;
    }

    // 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码
    private static String encode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8");
    }

    //获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
    private static String getContentType(File f) throws Exception {
        //return "application/octet-stream";  // 此行不再细分是否为图片，全部作为application/octet-stream 类型
        ImageInputStream imagein = ImageIO.createImageInputStream(f);
        if (imagein == null) {
            return "application/octet-stream";
        }
        Iterator<ImageReader> it = ImageIO.getImageReaders(imagein);
        if (!it.hasNext()) {
            imagein.close();
            return "application/octet-stream";
        }
        imagein.close();
        return "image/" + it.next().getFormatName().toLowerCase();//将FormatName返回的值转换成小写，默认为大写
    }

    //把文件转换成字节数组
    private static byte[] getBytes(File f) throws Exception {
        FileInputStream in = new FileInputStream(f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = in.read(b)) != -1) {
            out.write(b, 0, n);
        }
        in.close();
        return out.toByteArray();
    }

}
