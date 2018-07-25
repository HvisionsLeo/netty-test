package com.unitill.netty.client;

import com.unitill.constants.Constants;
import com.unitill.netty.NettyInitializer;
import com.unitill.redis.ClientSubscriber;
import com.unitill.util.ConfigUtils;
import com.unitill.util.RedisUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;


/**
 * @Description: 客户端
 * @Author: Leo
 * @Date: 2018-04-20 下午 4:17
 */
public class Client {

    private final static Logger LOGGER = LogManager.getLogger(Client.class);

    private static String HOST;

    private static int PORT;

    static {
        HOST = ConfigUtils.getString("client.host");
        PORT = ConfigUtils.getInteger("client1.port");
    }

    public void run() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try (Jedis subscribeJedis = RedisUtil.getJedis()) {
            Bootstrap boot = new Bootstrap();
            boot.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(HOST, PORT)
                    .handler(new NettyInitializer(true, boot));
            ChannelFuture future = boot.connect().sync();
            // 订阅报文 发送消息
            subscribeJedis.subscribe(new ClientSubscriber(future.channel()), Constants.BW_CHANNEL);
            LOGGER.info("Client Connect to " + HOST + ":" + PORT);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                eventLoopGroup.shutdownGracefully().sync();
                LOGGER.info("Client 优雅释放");
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Client().run();
    }
}
