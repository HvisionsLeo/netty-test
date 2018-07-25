package com.unitill.netty.codec;

import com.alibaba.fastjson.JSONObject;
import com.unitill.ibatis.pojo.PospMerTradeRecord;
import com.unitill.util.Dom4jXmlUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @Description: POS机传输信息解码器
 * @Author: Leo
 * @Date: 2018-05-04 上午 10:54
 */
public class PosMessageDecoder extends ByteToMessageDecoder{

    private final static Logger LOGGER = LogManager.getLogger(PosMessageDecoder.class);
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        try{
//        String orgNo = in.readBytes(5).toString(CharsetUtil.UTF_8);  //机构号
            String merNo= in.readBytes(23).toString(CharsetUtil.UTF_8);  //商户号
            String msgSize = in.readBytes(5).toString(CharsetUtil.UTF_8);  //报文长度
//        String psw = in.readBytes(32).toString(CharsetUtil.UTF_8);  //密钥
            String msgBody = in.readBytes(Integer.parseInt(msgSize)).toString(CharsetUtil.UTF_8); //报文主题
            LOGGER.info("msgBody:" + msgBody);
            PospMerTradeRecord model = (PospMerTradeRecord) Dom4jXmlUtil.fromXmlToBean(msgBody, PospMerTradeRecord.class);
            model.setMerId(merNo);
            out.add(JSONObject.toJSONString(model));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
