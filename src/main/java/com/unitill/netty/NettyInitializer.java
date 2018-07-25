package com.unitill.netty;

import com.unitill.netty.client.ClientHandler;
import com.unitill.netty.codec.PosMessageDecoder;
import com.unitill.netty.server.ServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: Leo
 * @Date: 2018-04-25 下午 1:50
 */
public class NettyInitializer extends ChannelInitializer<Channel> {

    private boolean isClient;
    private Bootstrap bootstrap;

    public NettyInitializer(boolean isClient, Bootstrap bootstrap) {
        this.isClient = isClient;
        this.bootstrap = bootstrap;
    }

    public NettyInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        if (isClient) {
            //向银行服务端发送心跳包，每三次发一串888888，保持连接
//            channel.eventLoop().scheduleAtFixedRate(()->{
//                    channel.writeAndFlush(Unpooled.copiedBuffer("888888".getBytes()));
//            },3, 3, TimeUnit.SECONDS);
            //心跳检测
//            pipeline.addLast(new IdleStateHandler(3, 3, 6, TimeUnit.SECONDS));
//            pipeline.addLast(new ClientHeartbeatHandler(bootstrap));
            pipeline.addLast(new JsonObjectDecoder());
            pipeline.addLast(new ClientHandler());
        } else {
            // 长度解码器
//            pipeline.addLast(new FixedLengthFrameDecoder(3));
//            pipeline.addLast(new JsonDecoder());
//            pipeline.addLast(new JsonObjectDecoder());
            pipeline.addLast(new PosMessageDecoder());
            pipeline.addLast(new ServerHandler());
        }
    }
}
