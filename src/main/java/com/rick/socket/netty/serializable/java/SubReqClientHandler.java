package com.rick.socket.netty.serializable.java;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(getSubReq(i));
        }
    }

    private SubReq getSubReq(int i) {
        SubReq req = new SubReq();
        req.setSubReqId(i);
        req.setAddress("光明城市");
        req.setPhoneNumber("18172858489");
        req.setProductName("Netty权威指南");
        req.setUserName("Rick");
        return req;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("SubReqClient accept server msg [" + ((SubResp) msg).toString() + "]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

