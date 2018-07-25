package com.unitill.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description: 获取配置文件内容
 * @Author: Leo
 * @Date: 2018-04-25 下午 2:12
 */
public class ConfigUtils {
    private static Properties properties = new Properties();

    static {
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(ConfigUtils.class.getResourceAsStream("/config.properties"));
            properties.load(bis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.getBoolean(properties.getProperty(key));
    }

    public static Integer getInteger(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }
}
