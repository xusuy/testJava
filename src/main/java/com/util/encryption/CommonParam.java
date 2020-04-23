package com.util.encryption;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class CommonParam {
    private String propertyFileName;
    private ResourceBundle resourceBundle;
    //private Logger logger = Logger.getLogger(CommonParam.class);
    
    public CommonParam(String fileName) {
        propertyFileName = fileName;
        resourceBundle = ResourceBundle.getBundle(propertyFileName);
    }
    public String getProperty(String key) {
        if (key == null || "".equals(key) || "null".equals(key)) {
            return "";
        }
        String result = "";
        try {
            result = resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
        return result;
    }
}