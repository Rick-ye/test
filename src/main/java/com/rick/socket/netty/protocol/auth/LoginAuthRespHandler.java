package com.rick.socket.netty.protocol.auth;

import com.rick.socket.netty.protocol.entry.Header;
import com.rick.socket.netty.protocol.entry.MessageType;
import com.rick.socket.netty.protocol.entry.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    private Map nodeCheck = new ConcurrentHashMap<String, String>();

    private String[] whiteList = {"127.0.0.1", "192.168.0.108"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        String remoteIP = ctx.channel().remoteAddress().toString();
        NettyMessage loginResp;
        if (message != null && message.getHeader() != null &&
                message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
            //重复登录，拒绝重复
            if (nodeCheck.containsKey(remoteIP)) {
                loginResp = buildResp((byte) -1);
            } else {
                boolean isOK = false;
                for (String wip : whiteList) {
                    if (wip.equals(remoteIP)) {
                        isOK = true;
                        break;
                    }
                }
                loginResp = isOK ? buildResp((byte) 1) : buildResp((byte) -1);
                if (isOK)
                    nodeCheck.put(remoteIP, true);
            }
            System.out.println("the login response is : " + loginResp + " body: [" + loginResp.getBody() + "]");
            ctx.writeAndFlush(loginResp);
        } else {
            ctx.fireChannelRead(msg);
        }
    }



    private NettyMessage buildResp(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }
}
