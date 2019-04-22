package com.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class IniProperties {
    private final static String filename = "D:\\project\\workspace-idea2017\\testJava\\src\\main\\resources\\ywconfig.ini";
    private transient Map<String, Object> sections = new HashMap();
    private transient String currentSecion;
    private transient Properties propertiesMap;

    private transient static IniProperties iniProperties = null;
    private transient static boolean falg = true;//防止通过反射构造方法创建对象

    private IniProperties() {
        if (falg) {
            falg = false;
        } else {
            throw new RuntimeException("IniProperties instance already exists!");
        }
    }

    /**
     * @throws IOException
     */
    private void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        read(reader);
        reader.close();
    }

    /**
     * 获取单例
     *
     * @return
     * @throws IOException
     */
    public static IniProperties getIniPropertiesInstance() throws IOException {
        if (iniProperties == null) {//双重检查锁，进行两次null检查。这样可以极大提升并发速度
            synchronized (IniProperties.class) {
                if (iniProperties == null) {
                    iniProperties = new IniProperties();
                    iniProperties.readFile();
                }
            }
        }
        return iniProperties;
    }

    //返序列化时直接返回当前实例
    private Object readResolve() {
        return iniProperties;
    }

    /**
     * 读取文件
     *
     * @param reader
     * @throws IOException
     */
    protected void read(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            parseLine(line);
        }
    }

    /**
     * 解析配置文件行
     *
     * @param line
     */
    @SuppressWarnings("unchecked")
    protected void parseLine(String line) {
        line = line.trim();
        if (line.matches("\\[.*\\]")) {
            currentSecion = line.replaceFirst("\\[(.*)\\]", "$1");
            propertiesMap = new Properties();
            sections.put(currentSecion, propertiesMap);
        } else if (line.matches(".*=.*")) {
            if (propertiesMap != null) {
                int i = line.indexOf('=');
                String name = line.substring(0, i).trim();
                String value = line.substring(i + 1).trim();
                propertiesMap.setProperty(name, value);
            }
        }
    }

    /**
     * 获取值
     *
     * @param section
     * @param name
     * @return
     */
    public String getValue(String section, String name) {
        Properties propertiesMap = (Properties) sections.get(section);
        if (propertiesMap == null) {
            return null;
        }
        String value = propertiesMap.getProperty(name);
        return value;
    }

    /**
     * 是否包含key
     *
     * @param section
     * @param key
     * @return
     */
    public boolean containsKey(String section, String key) {
        Properties propertiesMap = (Properties) sections.get(section);
        return propertiesMap.containsKey(key);
    }

}