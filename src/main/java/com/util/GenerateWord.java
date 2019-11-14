package com.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xsy
 * @create 2019-11-14 11:33
 * @desc 根据模板文件生成word文档
 **/
public class GenerateWord {
    @Test
    public void testWord1(){
        Map<String,Object> dataMap = new HashMap<String, Object>();
        try {
            //编号
            dataMap.put("policyNumber", "111111");
            dataMap.put("customNumber", "222222");
            //Configuration 用于读取ftl文件
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");

            /**
             * 以下是两种指定ftl文件所在目录路径的方式，注意这两种方式都是
             * 指定ftl文件所在目录的路径，而不是ftl文件的路径
             */
            //指定路径的第一种方式（根据某个类的相对路径指定）
                configuration.setClassForTemplateLoading(this.getClass(), "");

            //指定路径的第二种方式，我的路径是C：/a.ftl
            configuration.setDirectoryForTemplateLoading(new File("D:/study"));

            //输出文档路径及名称
            File outFile = new File("D:/study/理赔信息采集表.doc");

            //以utf-8的编码读取ftl文件
            Template template = configuration.getTemplate("cicf.ftl", "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
            template.process(dataMap, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
