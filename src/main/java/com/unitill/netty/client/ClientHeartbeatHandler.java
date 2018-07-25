package com.unitill.netty.client;

import com.unitill.netty.NettyInitializer;
import com.unitill.util.ConfigUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 客户端发送心跳
 * @Author: Leo
 * @Date: 2018-04-27 上午 10:27
 */
public class ClientHeartbeatHandler extends ChannelInboundHandlerAdapter {

    private final static Logger LOGGER = LogManager.getLogger(ClientHeartbeatHandler.class);
    private Bootstrap bootstrap;

    private static String HOST;

    private static int PORT;

    static {
        HOST = ConfigUtils.getString("client.host");
        PORT = ConfigUtils.getInteger("client1.port");
    }

    public ClientHeartbeatHandler(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                ctx.writeAndFlush(Unpooled.copiedBuffer("888888".getBytes()));
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("银行服务器关闭，30秒之后尝试重连。。。");
        ctx.channel().eventLoop().schedule(() -> {
            bootstrap.handler(new NettyInitializer(true, bootstrap));
            ChannelFuture future = bootstrap.connect(HOST, PORT);
            future.addListener((ChannelFutureListener) (f) -> {
                if (f.isSuccess()) {
                    LOGGER.info("重连成功。。。");
                } else {
                    LOGGER.info("重连失败，尝试再次重连");
                    f.channel().pipeline().fireChannelInactive();
                }
            });
        }, 30, TimeUnit.SECONDS);
    }
}
