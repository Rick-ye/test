package com.rick.socket.netty.protocol.codec;

import com.rick.socket.netty.protocol.entry.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Netty消息编码类
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder(MarshallingEncoder marshallingEncoder) {
        this.marshallingEncoder = marshallingEncoder;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        if (null == msg || null == msg.getHeader()) {
            throw new Exception("The encode message is null");
        }
        ByteBuf sendBuf = Unpooled.buffer();
        //---写入crcCode---
        sendBuf.writeInt((msg.getHeader().getCrcCode()));
        //---写入length---
        sendBuf.writeInt((msg.getHeader().getLength()));
        //---写入sessionId---
        sendBuf.writeLong((msg.getHeader().getSessionID()));
        //---写入type---
        sendBuf.writeByte((msg.getHeader().getType()));
        //---写入priority---
        sendBuf.writeByte((msg.getHeader().getPriority()));
        //---写入附件大小---
        sendBuf.writeInt((msg.getHeader().getAttachment().size()));

        String key = null;
        byte[] keyArray = null;
        Object value = null;
        Iterator<Map.Entry<String, Object>> it = msg.getHeader().getAttachment().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            key = entry.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = entry.getValue();
            marshallingEncoder.encode(value, sendBuf);
        }
        // for gc
        key = null;
        keyArray = null;
        value = null;

        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), sendBuf);
        } else
            sendBuf.writeInt(0);
        // 之前写了crcCode 4bytes，除去crcCode和length 8bytes即为更新之后的字节
        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }
}
