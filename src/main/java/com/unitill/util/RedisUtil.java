package com.unitill.util;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: redis工具类
 * @Author: Leo
 * @Date: 2018-04-20 上午 10:13
 */
public class RedisUtil {

    private static JedisPool jedisPool = null;

    //redis地址
    private static String REDIS_HOST;

    //端口号
    private static Integer PORT;

    //访问密码
    private static String AUTH;

    //超时时间
    private static Integer TIMEOUT;

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW;

    private static int MAX_TOTAL;

    static {
        REDIS_HOST = ConfigUtils.getString("redis.host");
        PORT = ConfigUtils.getInteger("redis.port");
        AUTH = ConfigUtils.getString("redis.auth");
        TIMEOUT = ConfigUtils.getInteger("redis.timeout");
        MAX_ACTIVE = ConfigUtils.getInteger("redis.max_active");
        MAX_IDLE = ConfigUtils.getInteger("redis.max_idle");
        MAX_WAIT = ConfigUtils.getInteger("redis.max_wait");
        TEST_ON_BORROW = ConfigUtils.getBoolean("redis.test_on_borrow");
        MAX_TOTAL = ConfigUtils.getInteger("redis.max_total");
        poolInit();
    }

    /**
     * 初始化连接池
     */
    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        config.setMaxTotal(MAX_TOTAL);
        if (StringUtils.isNotBlank(AUTH)) {
            jedisPool = new JedisPool(config, REDIS_HOST, PORT, TIMEOUT, AUTH);
        } else {
            // 没有登录密码
            jedisPool = new JedisPool(config, REDIS_HOST, PORT, TIMEOUT);
        }
    }

    /**
     * 在多线程同步初始化
     */
    private synchronized static void poolInit() {
        if (jedisPool == null) {
            initPool();
        }
    }

    /**
     * 同步获取jedis实例
     *
     * @return jedis实例
     */
    public synchronized static Jedis getJedis() {

        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jedis;
    }

}
