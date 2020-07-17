package com.rick.socket.netty.protocol.heart;

import com.rick.socket.netty.protocol.entry.Header;
import com.rick.socket.netty.protocol.entry.MessageType;
import com.rick.socket.netty.protocol.entry.NettyMessage;
import io.netty.channel.ChannelHandlerAppender;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 心跳请求消息
 */
public class HeartBeatReqHandler extends ChannelHandlerAppender {

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        //握手成功，开始发心跳消息
        if (message.getHeader() != null &&
                message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            heartBeat = ctx.executor().scheduleAtFixedRate(
                    new HeartBeatTask(ctx), 0, 5000, TimeUnit.SECONDS);

        } else if (message.getHeader() != null &&
                message.getHeader().getType() == MessageType.HART_BEAT_RESP.value()){
            System.out.println("Client receive server heart beat message: --->" + message);

        } else {
            ctx.fireChannelRead(msg);
        }

    }

    private NettyMessage buildNettyMessage() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HART_BEAT_REQ.value());
        message.setHeader(header);
        return message;
    }

    private class HeartBeatTask implements Runnable {

        private ChannelHandlerContext ctx;

        public HeartBeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage message = buildNettyMessage();
            System.out.println("Client send heart beat message to server: --->" + message);
            ctx.writeAndFlush(message);
        }
    }

}

