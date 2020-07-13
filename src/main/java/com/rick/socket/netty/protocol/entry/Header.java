package com.rick.socket.netty.protocol.entry;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息头POJO
 */
public class Header {

    //校验码
    private int crcCode = 0xabef0101;

    //消息总长度，包含消息头和消息体
    private int length;

    //集群内唯一会话ID
    private long sessionID;

    //消息类型：0-业务请求消息；1-业务响应消息；2-业务one-way消息(即使请求又是响应消息)；3握手请求消息；4握手应答消息；5心跳请求消息；6心跳应答消息
    private byte type;

    //优先级
    private byte priority;

    private Map attachment = new HashMap<String, Object>();

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map getAttachment() {
        return attachment;
    }

    public void setAttachment(Map attachment) {
        this.attachment = attachment;
    }
}
