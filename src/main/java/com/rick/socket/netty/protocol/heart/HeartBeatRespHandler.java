package com.rick.socket.netty.protocol.heart;

import com.rick.socket.netty.protocol.entry.Header;
import com.rick.socket.netty.protocol.entry.MessageType;
import com.rick.socket.netty.protocol.entry.NettyMessage;
import io.netty.channel.ChannelHandlerAppender;
import io.netty.channel.ChannelHandlerContext;

/**
 * 心跳响应消息
 */
public class HeartBeatRespHandler extends ChannelHandlerAppender {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null &&
                message.getHeader().getType() == MessageType.HART_BEAT_REQ.value()) {
            System.out.println("Server receive client heart beat message: ---> " + message);
            NettyMessage heartBeat = buildNettyMessage();
            System.out.println("Server send heart beat message to server: ---> " + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildNettyMessage() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HART_BEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}
