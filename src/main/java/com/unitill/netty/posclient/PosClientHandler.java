package com.unitill.netty.posclient;

import com.thoughtworks.xstream.XStream;
import com.unitill.ibatis.pojo.PospMerTradeRecord;
import com.unitill.util.IDGeneratorUtils;
import com.unitill.util.OrderIdGenUtils;
import com.unitill.util.PersonModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * @Description: 客户端处理器
 * @Author: Leo
 * @Date: 2018-04-20 下午 4:18
 */
public class PosClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final static Logger LOGGER = LogManager.getLogger(PosClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        XStream x = new XStream();
        x.alias("xmlPojo", PersonModel.class);
        PospMerTradeRecord posp = new PospMerTradeRecord();
        posp.setChannelCode("zhaoshangyinghang");
        posp.setMoney(new BigDecimal(10000));
        posp.setTerminalId("T01");
        posp.setFeeReg("hello");
        posp.setPaymentType("1");
        String xml = x.toXML(posp);
        int size = xml.length();
        String len = StringUtils.leftPad(String.valueOf(size),5,"0");
        String str = "67890" + len + xml;
        ByteBuf bf = Unpooled.copiedBuffer(str, CharsetUtil.UTF_8);
        LOGGER.info("POS Send message:" + str);
        ctx.writeAndFlush(bf);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        LOGGER.info("Server callback message:" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        LOGGER.error(cause.getMessage());
        ctx.close();
    }
}
