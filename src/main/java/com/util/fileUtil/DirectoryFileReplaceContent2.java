package com.util.fileUtil;

import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * @author xsy
 * @create 2019-03-13 15:55
 * @desc 替换目录文件夹下的指定内容（并替换目录文件夹）
 **/
public class DirectoryFileReplaceContent2 {

    public static void main(String[] args) throws IOException {
        String root_url = "D:\\work\\红猫二代-微服务\\一期\\ceshi\\app\\";
        File f1 = new File(root_url + "src\\main\\java\\cn\\cloud\\ceshi");
        print(f1, 0, 1);
        File f4 = new File(root_url + "src\\main\\res");
        print(f4, 0, 1);
        File f2 = new File(root_url + "build.gradle");
        print(f2, 0, 2);
        File f3 = new File(root_url + "src\\main\\AndroidManifest.xml");
        print(f3, 0, 2);
        File f5 = new File(root_url + "src\\main\\assets\\litepal.xml");
        print(f5, 0, 2);
        FileUtils.deleteDirectory(f1);//删除原来文件夹
    }

    /**
     * 遍历文件夹
     *
     * @param f
     * @param len
     * @param fileFalg 1代表文件夹；2代表文件
     */
    public static void print(File f, int len, int fileFalg) {
        if (fileFalg == 1) {
            File[] files = f.listFiles();

            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) { //判断是否文件夹
                    print(files[i], len + 1, fileFalg);
                }

                String theFilePath = files[i].getAbsolutePath();
                if (theFilePath.contains(".java") || theFilePath.contains(".xml")) {
                    File outPath = new File(files[i].getParent().replace("cloud\\ceshi", "cloud\\xinceshi"));

                    File readfile = new File(files[i].getAbsolutePath());

                    if (!readfile.isDirectory()) {
                        String filename = readfile.getName(); // 读到的文件名
                        String absolutepath = readfile.getAbsolutePath(); // 文件的绝对路径
                        readFile(absolutepath, filename, i, outPath); // 调用 readFile
                    }
                }
            }
        } else {
            File outPath = new File(f.getParent());
            File readfile = new File(f.getAbsolutePath());

            if (!readfile.isDirectory()) {
                String filename = readfile.getName(); // 读到的文件名
                String absolutepath = readfile.getAbsolutePath(); // 文件的绝对路径
                readFile(absolutepath, filename, 0, outPath); // 调用 readFile
            }
        }
    }

    /**
     * 读取文件夹下的文件
     *
     * @return
     */
    public static void readFile(String absolutepath, String filename,
                                int index, File outPath) {
        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(absolutepath), "utf-8")); // 数据流读取文件

            StringBuffer strBuffer = new StringBuffer();
            String newStr = "cn.cloud.xinceshi";
            String oldStr = "cn.cloud.ceshi";

            for (String temp = null; (temp = bufReader.readLine()) != null;
                 temp = null) {
                if ((temp.indexOf(oldStr) != -1) &&
                        (temp.indexOf(oldStr) != -1)) { // 判断当前行是否存在想要替换掉的字符
                    temp = temp.replace(oldStr, newStr); // 此处进行替换
                }

                strBuffer.append(temp);
                strBuffer.append(System.getProperty("line.separator")); // 换行符
            }

            bufReader.close();

            if (!outPath.exists()) { // 检查输出文件夹是否存在，若不存在先创建
                outPath.mkdirs();
                System.out.println("已成功创建输出文件夹：" + outPath);
            }

            PrintWriter printWriter = new PrintWriter(outPath + "\\" +
                    filename, "utf-8"); // 替换后输出文件路径
            printWriter.write(strBuffer.toString().toCharArray()); //重新写入
            printWriter.flush();
            printWriter.close();
            System.out.println("第 " + (index + 1) + " 个文件   " + absolutepath +
                    "  已成功输出到    " + outPath + "\\" + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
