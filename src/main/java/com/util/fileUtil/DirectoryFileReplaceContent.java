package com.util.fileUtil;

import java.io.*;

/**
 * @author xsy
 * @create 2019-01-17 16:42
 * @desc 替换目录文件夹下的指定内容（不替换目录文件夹）
 **/
public class DirectoryFileReplaceContent {

    public static void main(String[] args) {
        File f = new File("D:\\test\\OneCarAPI\\src\\main");
        print(f);
    }

    /**
     * 递归遍历文件
     *
     * @param f
     */
    public static void print(File f) {
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) { //判断是否文件夹
                print(files[i]);
            }

            // 覆盖原来的文件
            if (!files[i].isDirectory()) {
                readFile(files[i]); // 调用 readFile
            }
        }
    }

    /**
     * 读取文件夹下文件并替换内容
     *
     * @param file：文件
     */
    public static void readFile(File file) {
        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "utf-8")); // 数据流读取文件
            StringBuffer strBuffer = new StringBuffer();
            String oldStr = "cn.cloud.ceshi";
            String newStr = "cn.cloud.xinceshi";

            for (String temp; (temp = bufReader.readLine()) != null; ) {
                if ((temp.indexOf(oldStr) != -1) &&
                        (temp.indexOf(oldStr) != -1)) { // 判断当前行是否存在想要替换掉的字符
                    temp = temp.replace(oldStr, newStr); // 此处进行替换
                }

                strBuffer.append(temp);
//                strBuffer.append(System.getProperty("line.separator")); // 换行符
            }

            bufReader.close();

            PrintWriter printWriter = new PrintWriter(file, "utf-8"); // 替换后输出文件路径
            printWriter.write(strBuffer.toString().toCharArray()); //重新写入
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
