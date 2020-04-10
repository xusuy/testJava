package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * @author xsy
 * @create 2020-04-10 13:55
 * @desc 文件相关
 **/
public class FileUtil {
    /**
     * 创建文件夹及文件
     *
     * @param suffixText 后缀类型（pdf、doc、docx、png、jpg）
     */
    public static File mkDirectoryAndFile(String suffixText) {
        String uuid = UUIDGenerator.sequentialUUIDString();
        String pdfPath = FileUtil.class.getResource("/").getPath()
                .concat("files/template");
        File file = new File(pdfPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = uuid.concat(".").concat(suffixText);
        File writeFile = new File(pdfPath, fileName);
        if (!writeFile.exists()) {
            try {
                writeFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // 指定文件格式为utf-8
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(writeFile), StandardCharsets.UTF_8);
            writer.write("");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return writeFile;
    }
}
