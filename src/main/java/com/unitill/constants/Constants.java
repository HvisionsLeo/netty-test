package com.unitill.constants;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Leo
 * @Date: 2018-04-26 下午 12:20
 */
public class Constants {

    public final static ApplicationContext CTX;
    public final static String BW_CHANNEL = "bw";
    public static Map<String, String> CATCH_MAP = new HashMap<>();
    public static Map<String, String> CATCH_CLIENT_MAP = new HashMap<>();

    static {
        System.out.println("初始化CTX！");
        CTX =  new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
