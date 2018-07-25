package com.unitill.file;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.io.File;

/**
 * @Description: 文件上传客户端
 * @Author: Leo
 * @Date: 2018-05-02 上午 10:09
 */
public class FileUploadClient {

    private String HOST;
    private int PORT;

    public FileUploadClient(String HOST, int PORT) {
        this.HOST = HOST;
        this.PORT = PORT;
    }

    public void run(FileUploadFile uploadFile) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap boot = new Bootstrap();
        try {
            boot.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ObjectDecoder objectDecoder = new ObjectDecoder(1024 * 1024 * 1000,
                                    ClassResolvers.weakCachingConcurrentResolver(this
                                            .getClass().getClassLoader()));
                            ch.pipeline().addLast(new ObjectEncoder())
                                    .addLast(objectDecoder)
                                    .addLast(new FileUploadClientHandler(uploadFile));
                        }
                    });

            ChannelFuture future = boot.connect(HOST, PORT).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        FileUploadFile uploadFile = new FileUploadFile();
        File file = new File("D:\\tools\\cn_office_professional_plus_2013_x86_dvd_1134005.iso");
        String fileMd5 = file.getName();// 文件名
        uploadFile.setFile(file);
        uploadFile.setFileMd5(fileMd5);
        uploadFile.setStartPos(0);// 文件开始位置
        new FileUploadClient("127.0.0.1", 8088).run(uploadFile);
    }
}
