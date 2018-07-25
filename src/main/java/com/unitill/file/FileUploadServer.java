package com.unitill.file;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @Description: 文件上传服务端
 * @Author: Leo
 * @Date: 2018-05-02 上午 11:12
 */
public class FileUploadServer {

    private int PORT;

    public FileUploadServer(int PORT) {
        this.PORT = PORT;
    }

    public void run() {
        EventLoopGroup bossEventloopGroup = new NioEventLoopGroup();
        EventLoopGroup worksEventloopGroup = new NioEventLoopGroup();
        ServerBootstrap boots = new ServerBootstrap();
        try {
            boots.group(bossEventloopGroup, worksEventloopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ObjectDecoder objectDecoder = new ObjectDecoder(1024 * 1024 * 1000,
                                ClassResolvers.weakCachingConcurrentResolver(this
                                        .getClass().getClassLoader()));

                            ch.pipeline().addLast(new ObjectEncoder())
                                    .addLast(objectDecoder)
                                    .addLast(new FileUploadServerHandler());
                        }
                    });
            ChannelFuture future = boots.bind(PORT).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                worksEventloopGroup.shutdownGracefully().sync();
                bossEventloopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new FileUploadServer(8088).run();
    }
}
