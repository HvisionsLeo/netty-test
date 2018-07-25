package com.unitill.redis;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.JedisPubSub;

/**
 * @Description: 订阅者
 * @Author: Leo
 * @Date: 2018-04-20 上午 11:31
 */
public class ServerSubscriber extends JedisPubSub {

    private final static Logger LOGGER = LogManager.getLogger(ServerSubscriber.class);
    private ChannelHandlerContext ctx;

    public ServerSubscriber(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    /**
     * 收到消息
     * @param channel 管道
     * @param message 接收到的消息
     */
    @Override
    public void onMessage(String channel, String message) {
        LOGGER.info(Thread.currentThread().getName() + ",received channel：" +channel + ",message：" + message);
        this.unsubscribe();
        //下发给pos机,并关闭连接
        ctx.write(Unpooled.copiedBuffer("success".getBytes()));
    }

    /**
     * 订阅时调用
     * @param channel 订阅的管道
     * @param subscribedChannels 管道个数
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        LOGGER.info("server subscribe channel：" + channel + ", subscribedChannels：" + subscribedChannels);
    }

    /**
     * 取消订阅时调用
     * @param channel 取消订阅的管道
     * @param subscribedChannels  取消的个数
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        LOGGER.info("server unSubscribe channel：" + channel + ", subscribedChannels：" + subscribedChannels);
    }
}
