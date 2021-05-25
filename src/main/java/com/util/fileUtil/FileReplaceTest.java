package com.util.fileUtil;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.File;

/**
 * @Date 2021/5/25 9:33
 * @Version 1.0
 * @Describe 换皮：将A文件夹下的图片 替换到 B文件下的各目录中(A和B中文件名必须对应)
 */
public class FileReplaceTest {
    //需替换图片文件
    public static final String SOURCE_FILE = "D:\\project\\work\\game\\source\\521\\lcss_displace";
    //目标文件
    public static final String TARGET_FILE = "D:\\project\\work\\game\\source\\521\\lcss\\resource";
//    //需替换图片文件
//    public static final String SOURCE_FILE = "D:\\project\\work\\game\\source\\521\\testSource";
//    //目标文件
//    public static final String TARGET_FILE = "D:\\project\\work\\game\\source\\521\\testTarget";

    @Test
    public void testDisplace() {
        StopWatch time = new StopWatch("file displace");
        time.start();
        File sourceFile = new File(SOURCE_FILE);
        if (sourceFile.isDirectory()) {
            File[] imgFiles = sourceFile.listFiles();//获取路径下的所有文件(包括文件夹与文件)
            int successCount = 0;
            for (File sourceImgFile : imgFiles) {
                if (!sourceImgFile.isDirectory()) {
                    String searchFileName = sourceImgFile.getName();
                    File targetFile = searchTargetFile(TARGET_FILE, searchFileName);
                    if (targetFile != null) {
                        if (targetFile.exists()) {
                            targetFile.delete();
                        }
                        sourceImgFile.renameTo(targetFile);
                        successCount++;
                    } else {
                        System.out.println(String.format("%s不存在于%s目录中", searchFileName, TARGET_FILE));
                    }
                }
            }
            time.stop();
            System.out.println(String.format("需要替换的文件数量%d,成功替换的数量%d,耗时%f秒", imgFiles.length, successCount, time.getTotalTimeSeconds()));
        } else {
            throw new RuntimeException("需替换图片文件 必须是目录！");
        }
    }

    //遍历查询目标文件
    private File searchTargetFile(String searchFileDir, String searchFileName) {
        File searchTargetFile = null;
        File targetFileDir = new File(searchFileDir);
        if (targetFileDir.isDirectory()) {
            for (File f : targetFileDir.listFiles()) {
                if (!f.isDirectory()) {
                    if (f.getName().endsWith(searchFileName)) {
                        searchTargetFile = f;
                        break;
                    }
                } else {
                    File returnFile = searchTargetFile(f.getAbsolutePath(), searchFileName);
                    if (returnFile != null) {
                        return returnFile;
                    }
                }
            }
            return searchTargetFile;
        } else {
            throw new RuntimeException("目标文件 必须是目录！");
        }
    }

    //    @Test
    public void testMove() {
        String source = "D:\\project\\work\\game\\source\\521\\backBtn.png";
//        File sourceFile = new File(SOURCE_FILE);
//        System.out.println(sourceFile.getName());
        String target = "D:\\project\\work\\game\\source\\521\\test\\backBtn.png";

        File sourceFile = new File(source);
        File targerFile = new File(target);
        if (targerFile.exists()) {
            targerFile.delete();
        }
        sourceFile.renameTo(targerFile);

    }
}
