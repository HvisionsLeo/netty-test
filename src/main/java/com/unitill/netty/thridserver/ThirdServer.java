package com.unitill.netty.thridserver;

import com.unitill.util.ConfigUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Description: 模拟第三方
 * @Author: Leo
 * @Date: 2018-04-26 下午 1:37
 */
public class ThirdServer {
    private final static Logger LOGGER = LogManager.getLogger(ThirdServer.class);

    private static int PORT;

    static {
        PORT = ConfigUtils.getInteger("server1.port");
    }

    public void run() {
        EventLoopGroup bossEventGroup = new NioEventLoopGroup();
        EventLoopGroup worksEventGroup = new NioEventLoopGroup();
        ServerBootstrap boot = new ServerBootstrap();
        boot.group(bossEventGroup, worksEventGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new JsonObjectDecoder()).addLast(new ThirdServerHandler());
                    }
                });
        try {
            ChannelFuture future = boot.bind(PORT).sync();
            LOGGER.info("Server start at port:" + PORT);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                worksEventGroup.shutdownGracefully().sync();
                bossEventGroup.shutdownGracefully().sync();
                LOGGER.info("Third Server优雅释放");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
