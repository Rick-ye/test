package com.rick.socket.netty.protocol.entry;

public enum MessageType {

    SERVICE_REQ("业务请求消息", 0),
    SERVICE_RESP("业务应答消息", 1),
    ONE_WAY("业务one-way消息", 2),
    LOGIN_REQ("握手请求消息", 3),
    LOGIN_RESP("握手应答消息", 4),
    HART_BEAT_REQ("心跳请求消息", 5),
    HART_BEAT_RESP("心跳应答消息", 6);

    private String type;

    private int index;

    public byte value() {
        return (byte) index;
    }

    MessageType(String type, int index) {
        this.index = index;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
