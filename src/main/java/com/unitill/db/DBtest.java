package com.unitill.db;

import com.unitill.service.PospMerTradeRecordService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Description:
 * @Author: Leo
 * @Date: 2018-04-25 下午 4:54
 */
public class DBtest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PospMerTradeRecordService service = (PospMerTradeRecordService) ctx.getBean("pospMerTradeRecordService");
        StringRedisTemplate template = (StringRedisTemplate) ctx.getBean("stringRedisTemplate");
        template.opsForList().leftPush("key","123");
        System.out.println(template.opsForList().rightPop("key"));
    }
}
