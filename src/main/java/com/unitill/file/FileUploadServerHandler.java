package com.unitill.file;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @Description: 文件上传服务端处理器
 * @Author: Leo
 * @Date: 2018-05-02 上午 11:12
 */
public class FileUploadServerHandler extends ChannelInboundHandlerAdapter{

    private volatile int start = 0;

    /**
     * 读取文件，保存在服务器
     * @param ctx ChannelHandlerContext
     * @param msg 接收到的消息
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FileUploadFile){
            FileUploadFile uploadFile = (FileUploadFile) msg;
            String path = "F:"+ File.separator + uploadFile.getFileMd5();
            int byteRead = uploadFile.getEndPos();
            File file = new File(path);
            try(RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")){
                if (byteRead > 0){
                    randomAccessFile.seek(start);
                    randomAccessFile.write(uploadFile.getBytes());
                    start = start + byteRead;
                    ctx.writeAndFlush(start);
                }
            }
        }
    }

    /**
     * 读取完毕将连接关闭
     * @param ctx ChannelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(Unpooled.EMPTY_BUFFER);
    }

    /**
     * 异常处理
     * @param ctx ChannelHandlerContext
     * @param cause 异常
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
