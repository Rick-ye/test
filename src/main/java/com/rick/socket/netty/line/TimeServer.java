package com.rick.socket.netty.line;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * netty提供一下四种抽象的方式解决tcp粘包拆包问题
 * 1、LineBasedFrameDecoder组件以换行符为标志进行解决
 * 2、消息固定长度
 * 3、以特殊的分隔符作为消息的结束标志
 * 4、将消息分为消息头和消息体，消息体中定义长度字段来标识消息的总长度
 *
 * 测试LineBasedFrameDecoder 换行符解决tcp粘包拆包问题
 */
public class TimeServer {

    public void bind(int port) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)  //类似jdk nio类库中的ServerSocketChannel
                    .option(ChannelOption.SO_BACKLOG, 1024)  //设置tcp中的参数
                    .childHandler(new ChildChannelHandler());  //绑定I/O事件的处理类，类似react模式的handler类

            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();

            //阻塞 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (Exception e) {

        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel sc) throws Exception {
            /*LineBasedFrameDecoder的原理是：一次遍历ByteBuf的可读字节，判断看是否有"\n"或者
            "\r\n"，如果有，就以此为结束位置。是以换行符为结束标志的解码器。同时支持配置单行的最大长度，
            如果读取到最大长度还没有换行符就抛出异常
             */
            //LineBasedFrameDecoder + StringDecoder就是按行切换的文本解码器组合。它们被设计用来支持TCP的粘包拆包
            sc.pipeline().addLast(new LineBasedFrameDecoder(1024));
            sc.pipeline().addLast(new StringDecoder());

            sc.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) {
        new TimeServer().bind(8888);
    }
}
