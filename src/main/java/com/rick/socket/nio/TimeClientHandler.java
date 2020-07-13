package com.rick.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable {

    private int port;
    private String host;
    private boolean stop = false;
    private SocketChannel clientChannel;
    private Selector selector;

    public TimeClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            selector = Selector.open();
            clientChannel = SocketChannel.open();
            clientChannel.configureBlocking(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);     //链接失败退出进程
        }
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try {
                        handlerInput(key);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (Exception e) {

            }
        }
    }

    private void handlerInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //判断链接是否成功
            SocketChannel channel = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (channel.finishConnect()) {
                    channel.register(selector, SelectionKey.OP_READ);
                    doWrite(channel);
                } else {
                    System.exit(1);
                }
            }
            if (key.isReadable()) {
                ByteBuffer allocate = ByteBuffer.allocate(1024);
                int read = channel.read(allocate);
                if (read > 0) {
                    allocate.flip();
                    byte[] bytes = new byte[allocate.remaining()];
                    allocate.get(bytes);
                    String str = new String(bytes, "UTF-8");
                    System.out.println(str);
                } else if (read < 0) {
                    key.cancel();
                    clientChannel.close();
                } else {

                }
            }
        }
    }

    public void doWrite(SocketChannel clientChannel) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        String date = new Date().toString();
        byte[] bytes = date.getBytes();
        allocate.put(bytes);
        allocate.flip();
        clientChannel.write(allocate);
        if (!allocate.hasRemaining()) {
            System.out.println("send msg succeed");
        }//没有考虑没写完数据的情况
         else {}
    }

    public void doConnect() throws IOException {
        if (clientChannel.connect(new InetSocketAddress(host, port))) {
            clientChannel.register(selector, SelectionKey.OP_READ);
            doWrite(clientChannel);
        } else {
            clientChannel.register(selector, SelectionKey.OP_CONNECT);
        }

    }
}
