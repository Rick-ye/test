package com.rick.socket.netty.separation;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 1.DelimiterBasedFrameDecoder 分隔符解码解决粘包拆包
 * 2.FixedLengthFrameDecoder 固定长度解决粘包拆包
 */
public class EchoServer {

    public void bind(int port) throws InterruptedException {
        // NioEventLoopGroup就是react线程组，boosGroup用于服务端接受客户端的连接，workGroup用于进行socketChannel的网络读写
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //用于启动nio服务端的辅助启动类
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)  //类似jdk nio类库中的ServerSocketChannel
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //绑定I/O事件的处理类，类似react模式的handler类，主要用于处理网络io事件
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                            //1024指在读取到1024字节的数据以后，还没有找到指定的分隔符就抛出异常
                            //ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                            //测试固定长度的方式解码
                            ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            //绑定端口，同步等待成功
            ChannelFuture sync = b.bind(port).sync();
            //等待服务端监听端口关闭
            sync.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8888;
        new EchoServer().bind(port);
    }
}
