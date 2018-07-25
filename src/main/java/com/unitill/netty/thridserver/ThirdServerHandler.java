package com.unitill.netty.thridserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Description:
 * @Author: Leo
 * @Date: 2018-04-26 下午 1:54
 */
public class ThirdServerHandler extends ChannelInboundHandlerAdapter {

    private final static Logger LOGGER = LogManager.getLogger(ThirdServerHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf bf = (ByteBuf) msg;
            LOGGER.info("Third received message:" + bf.toString(CharsetUtil.UTF_8));
            ctx.write(Unpooled.copiedBuffer(bf.toString(CharsetUtil.UTF_8), CharsetUtil.UTF_8));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
