package com.unitill.redis;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.JedisPubSub;

/**
 * @Description: 客户端订阅者
 * @Author: Leo
 * @Date: 2018-04-20 上午 11:31
 */
public class ClientSubscriber extends JedisPubSub {

    private final static Logger LOGGER = LogManager.getLogger(ClientSubscriber.class);
    private Channel channel;

    public ClientSubscriber(Channel channel) {
        this.channel = channel;
    }

    /**
     * 收到消息
     * @param ch 管道
     * @param message 接收到的消息
     */
    @Override
    public void onMessage(String ch, String message) {
        LOGGER.info(Thread.currentThread().getName() + ",recived ch：" +ch + ",message：" + message);

        channel.writeAndFlush(Unpooled.copiedBuffer(message.getBytes()));
    }

    /**
     * 订阅时调用
     * @param ch 订阅的管道
     * @param subscribedChannels 管道个数
     */
    @Override
    public void onSubscribe(String ch, int subscribedChannels) {
        LOGGER.info("client subscribe ch：" + ch + ", subscribedChannels：" + subscribedChannels);
    }

    /**
     * 取消订阅时调用
     * @param ch 取消订阅的管道
     * @param subscribedChannels  取消的个数
     */
    @Override
    public void onUnsubscribe(String ch, int subscribedChannels) {
        LOGGER.info("client unSubscribe ch：" + ch + ", subscribedChannels：" + subscribedChannels);
    }
}
