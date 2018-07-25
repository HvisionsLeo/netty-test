package com.unitill.util;

import com.thoughtworks.xstream.XStream;

/**
 * @Description: Xstream解析xml工具类
 * @Author: Leo
 * @Date: 2018-05-04 下午 2:05
 */
public class XstreamXmlUtil {

    public static void main(String[] args) {
        XStream x = new XStream();
        x.alias("xmlPojo",PersonModel.class);
        PersonModel model = new PersonModel();
        model.setAge("20");
        model.setName("lxn");
        String xml = x.toXML(model);
        System.out.println(xml);
    }
}
