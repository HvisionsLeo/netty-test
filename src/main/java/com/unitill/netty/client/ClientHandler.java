package com.unitill.netty.client;

import com.alibaba.fastjson.JSONObject;
import com.unitill.constants.Constants;
import com.unitill.ibatis.pojo.PospMerTradeRecord;
import com.unitill.service.PospMerTradeRecordService;
import com.unitill.util.RedisUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * @Description: 客户端处理器
 * @Author: Leo
 * @Date: 2018-04-20 下午 4:18
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final static Logger LOGGER = LogManager.getLogger(ClientHandler.class);

    private PospMerTradeRecordService pospMerTradeRecordService = (PospMerTradeRecordService) Constants.CTX.getBean("pospMerTradeRecordService");

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        try (Jedis publishJedis = RedisUtil.getJedis()) {
            LOGGER.info("Third Server callback message:" + byteBuf.toString(CharsetUtil.UTF_8));
            //获取到第三方的数据，对表中数据进行更新
            PospMerTradeRecord pospMerTradeRecord = JSONObject.parseObject(byteBuf.toString(CharsetUtil.UTF_8), PospMerTradeRecord.class);
            {
                pospMerTradeRecord.setOrderStatus("0");
                pospMerTradeRecord.setRetData("成功");
                pospMerTradeRecord.setRetCode("0");
                String bankOrderId = (pospMerTradeRecord.getMerOrderId() + new Date().getTime()).substring(0, 32);
                pospMerTradeRecord.setBankOrderId(bankOrderId);

                pospMerTradeRecordService.update(pospMerTradeRecord);
            }
            //将结果返回发布
            publishJedis.publish(pospMerTradeRecord.getTerminalId(), byteBuf.toString(CharsetUtil.UTF_8));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        LOGGER.error(cause.getMessage());
        ctx.close();
    }
}
