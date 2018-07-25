package com.unitill.netty.posclient;

import com.unitill.util.ConfigUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 模拟pos机
 * @Author: Leo
 * @Date: 2018-04-26 下午 1:37
 */
public class PosClient {
    private final static Logger LOGGER = LogManager.getLogger(PosClient.class);

    private static ExecutorService service = Executors.newFixedThreadPool(4);

    private static String HOST;

    private static int PORT;

    static {
        HOST = ConfigUtils.getString("client.host");
        PORT = ConfigUtils.getInteger("client.port");
    }

    public void run() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap boot = new Bootstrap();
            boot.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(HOST, PORT)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO)).addLast(new PosClientHandler());
                        }
                    });
            ChannelFuture future = boot.connect().sync();
            LOGGER.info("POS Client Connect to " + HOST + ":" + PORT);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                eventLoopGroup.shutdownGracefully().sync();
                LOGGER.info("Pos优雅释放");
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++)
            service.execute(() -> new PosClient().run());
    }
}
