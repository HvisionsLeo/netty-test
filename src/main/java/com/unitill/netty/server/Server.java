package com.unitill.netty.server;

import com.unitill.netty.NettyInitializer;
import com.unitill.util.ConfigUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: netty服务端
 * @Author: Leo
 * @Date: 2018-04-20 下午 3:35
 */
public class Server {

    private final static Logger LOGGER = LogManager.getLogger(Server.class);
    private static int PORT;
    static {
        PORT = ConfigUtils.getInteger("server.port");
    }


    public void run() {
        EventLoopGroup bossEventGroup = new NioEventLoopGroup();
        EventLoopGroup worksEventGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(bossEventGroup, worksEventGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyInitializer(false));
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
                LOGGER.info("Server优雅释放");
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Server().run();
    }
}
