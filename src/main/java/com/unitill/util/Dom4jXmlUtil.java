package com.unitill.util;


import com.sun.xml.internal.ws.util.xml.XmlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: dom4j解析xml工具
 * @Author: Leo
 * @Date: 2018-05-04 上午 11:34
 */
public class Dom4jXmlUtil {

    private final static Logger LOGGER = LogManager.getLogger(Dom4jXmlUtil.class);

    /**
     * xml转实体
     *
     * @param xmlStr xml内容
     * @param clazz  实体对象
     * @return 实体
     * @throws Exception 异常
     */
    public static Object fromXmlToBean(String xmlStr, Class clazz) throws Exception {
        Document doc = DocumentHelper.parseText(xmlStr);
        Element rootElt = doc.getRootElement();
        LOGGER.info("根节点：" + rootElt.getName());
        Field[] fields = clazz.getDeclaredFields();
        Object obj = clazz.newInstance();
        for (Field field : fields) {
            // 设置字段访问
            field.setAccessible(true);
            String name = field.getName();
            rootElt.elementTextTrim(name);
            if (rootElt.elementTextTrim(name) != null && !"".equals(rootElt.elementTextTrim(name))) {
                if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
                    field.set(obj, Long.parseLong(rootElt.elementTextTrim(name)));
                } else if (field.getType().equals(String.class)) {
                    field.set(obj, rootElt.elementTextTrim(name));
                } else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
                    field.set(obj, Double.parseDouble(rootElt.elementTextTrim(name)));
                } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                    field.set(obj, Integer.parseInt(rootElt.elementTextTrim(name)));
                } else if (field.getType().equals(java.util.Date.class)) {
                    field.set(obj, Date.parse(rootElt.elementTextTrim(name)));
                } else if (field.getType().equals(BigDecimal.class)) {
                    field.set(obj, new BigDecimal(rootElt.elementTextTrim(name)));
                } else {
                    continue;
                }
            }
        }
        return obj;
    }

    /**
     * 对象转换为xml字符串
     * @param clazz 实体对象
     * @return xml字符串
     * @throws Exception 抛出异常
     */
    public static String fromBeanToXml(Object clazz) throws Exception {
        Document doc = DocumentHelper.createDocument();
        Element rootElt = doc.addElement("xmlPojo");
        Field[] fields = clazz.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if(!"serialVersionUID".equals(name)){
                name = name.substring(0,1).toUpperCase()+name.substring(1);
                Method method = clazz.getClass().getMethod("get" + name);
                String value = (String) method.invoke(clazz);
                Element ele = rootElt.addElement(name);
                ele.setText(value);
            }
        }
        return doc.getText();
    }

    public static void main(String[] args) throws Exception {

        String xml = "<xmlPojo><name>test</name><age>23</age></xmlPojo>";
        PersonModel model = (PersonModel) Dom4jXmlUtil.fromXmlToBean(xml, PersonModel.class);
        System.out.println(model.toString());
        System.out.println(Dom4jXmlUtil.fromBeanToXml(model));
    }
}
