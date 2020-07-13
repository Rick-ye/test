package com.rick.socket.netty.line;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * netty server Handler
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    //测试TCP粘包/拆包——客户端发送100条消息，服务端只能收到2消息，说明发生粘包
    private int counter;

    /**
     * 当请求连接成功后，调用该方法读取数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        /*//使用netty LineBasedFrameDecoder解决粘包拆包之前
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());*/

        //使用netty LineBasedFrameDecoder解决粘包拆包之后，无需考虑分隔符，并且StringDecoder已经将消息转换成string类型
        String body = (String) msg;
        System.out.println("The time server receive: " + body + "; the counter is: " + ++counter);
        String currentTime = "QUERY TIME ORDER".equals(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
    }

    /**
     * 当数据读取完成时，将消息发送队列中的消息写入到socketChannel中发送给对方
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    /**
     * 当发生异常时，将关闭ChannelHandlerContext，释放相关句柄资源
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
