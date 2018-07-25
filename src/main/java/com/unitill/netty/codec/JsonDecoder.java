package com.unitill.netty.codec;

import com.unitill.constants.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @Description:
 * @Author: Leo
 * @Date: 2018-04-27 下午 12:11
 */
public class JsonDecoder extends StringDecoder{


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        //判断是不是以{}开始和结束的；
        String value = msg.toString(CharsetUtil.UTF_8);
        String pre_str = Constants.CATCH_MAP.get(ctx.channel().id().toString());
        String all_str = (StringUtils.isNotBlank(pre_str) ? pre_str:"") + value;
        all_str = all_str.replaceAll("}","};");
        String[] ss = all_str.split(";");
        for (String s : ss) {
            if (s.startsWith("{") && s.endsWith("}")){
                out.add(s);
            }else{
                Constants.CATCH_MAP.put(ctx.channel().id().toString(), s);
            }
        }
    }

}
