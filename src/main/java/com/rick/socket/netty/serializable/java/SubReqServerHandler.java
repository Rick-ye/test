package com.rick.socket.netty.serializable.java;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubReq req = (SubReq) msg;
        SubResp resp = new SubResp();
        resp.setSubReqId(req.getSubReqId());
        resp.setRespCode(200);
        resp.setDesc("Netty book order success.");
        System.out.println("SubReqServer accept client req: [" + req.toString() + "]");
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
