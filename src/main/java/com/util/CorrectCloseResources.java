package com.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//正确关闭资源
public class CorrectCloseResources {

    // 使用try-with-resources(关闭资源最好的方式) 代替try-finally
    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }
}
