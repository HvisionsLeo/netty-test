package com.unitill.netty.server;

import com.alibaba.fastjson.JSONObject;
import com.unitill.constants.Constants;
import com.unitill.ibatis.dao.PospMerFeeMapper;
import com.unitill.ibatis.pojo.PospMerFee;
import com.unitill.ibatis.pojo.PospMerShareRecord;
import com.unitill.ibatis.pojo.PospMerTradeRecord;
import com.unitill.ibatis.pojo.PospTerminal;
import com.unitill.redis.ServerSubscriber;
import com.unitill.service.PospMerShareRecordService;
import com.unitill.service.PospMerTradeRecordService;
import com.unitill.service.PospTerminalService;
import com.unitill.util.FeeUtils;
import com.unitill.util.IDGeneratorUtils;
import com.unitill.util.OrderIdGenUtils;
import com.unitill.util.RedisUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.List;


/**
 * @Description: 服务器处理器
 * @Author: Leo
 * @Date: 2018-04-20 下午 3:35
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static PospMerTradeRecordService pospMerTradeRecordService = (PospMerTradeRecordService) Constants.CTX.getBean("pospMerTradeRecordService");
    private static PospMerFeeMapper pospMerFeeMapper = (PospMerFeeMapper) Constants.CTX.getBean("pospMerFeeMapper");
    private static PospMerShareRecordService pospMerShareRecordService = (PospMerShareRecordService) Constants.CTX.getBean("pospMerShareRecordService");
    private static PospTerminalService pospTerminalService = (PospTerminalService) Constants.CTX.getBean("pospTerminalService");
    private final static Logger LOGGER = LogManager.getLogger(ServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try (Jedis publishJedis = RedisUtil.getJedis(); Jedis subscribeJedis = RedisUtil.getJedis()) {
            String s = (String) msg;
            LOGGER.info(s);
//            ByteBuf bf = (ByteBuf) msg;
//            LOGGER.info("received message:" + bf.toString(CharsetUtil.UTF_8));
            PospMerTradeRecord pospMerTradeRecord = JSONObject.parseObject(s, PospMerTradeRecord.class);

            List<PospTerminal> terList = pospTerminalService.findTerByMerId(pospMerTradeRecord.getMerId(), pospMerTradeRecord.getTerminalId());
            if(terList.size() <= 0 || terList == null) {
                throw new Exception("终端编号和商户号不匹配或终端处于非正常状态！");
            }
            PospMerFee fee = pospMerFeeMapper.selectByMerNo(pospMerTradeRecord.getMerId());
            if(fee == null) {
                throw new Exception("未查询到该商户的商户费率！");
            }
            BigDecimal feeMoney = FeeUtils.toFee(fee, pospMerTradeRecord.getMoney());
            {
                // 存放trade
                pospMerTradeRecord.setFeeMoney(feeMoney);
                pospMerTradeRecord.setId(IDGeneratorUtils.genId());
                pospMerTradeRecord.setOrderStatus("1");//默认为进行中
                pospMerTradeRecord.setMerOrderId(OrderIdGenUtils.genOutPayment());
                pospMerTradeRecord.setBankFeeMoney(new BigDecimal(2));//银行手续费先写死
                pospMerTradeRecord.setFeeReg(fee.getFeeName());
                pospMerTradeRecordService.insert(pospMerTradeRecord);
            }

            {
                // 存放share
                PospMerShareRecord shareRecord = new PospMerShareRecord();
                shareRecord.setId(IDGeneratorUtils.genId());
                shareRecord.setMerId(pospMerTradeRecord.getMerId());
                shareRecord.setTerminalId(pospMerTradeRecord.getTerminalId());
                shareRecord.setMerOrderId(pospMerTradeRecord.getMerOrderId());
                shareRecord.setChannelCode(pospMerTradeRecord.getChannelCode());
                shareRecord.setShareIs(BigDecimal.ONE);
                shareRecord.setMoney(pospMerTradeRecord.getMoney());
                shareRecord.setBankFeeMoney(pospMerTradeRecord.getBankFeeMoney());
                shareRecord.setFeeMoney(feeMoney);
                shareRecord.setSummary(pospMerTradeRecord.getSummary());
                shareRecord.setRemark(pospMerTradeRecord.getRemark());
                shareRecord.setPaymentType(pospMerTradeRecord.getPaymentType());
                pospMerShareRecordService.insert(shareRecord);
            }
            //发布消息到bw
            publishJedis.publish(Constants.BW_CHANNEL, JSONObject.toJSONString(pospMerTradeRecord));
            //订阅redis中的pospMerTradeRecord.getTerminalId()
            subscribeJedis.subscribe(new ServerSubscriber(ctx), pospMerTradeRecord.getTerminalId());

        } catch (Exception e) {
            e.printStackTrace();
            ctx.write(Unpooled.copiedBuffer(e.getMessage().getBytes()));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        LOGGER.error(cause.getMessage());
        ctx.close();
    }
}
