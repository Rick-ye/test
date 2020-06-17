package com.rick.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 多路复用器服务程序
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;
    private ServerSocketChannel socketChannel;
    private boolean stop = false;

    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.bind(new InetSocketAddress("127.0.0.1", port));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("TimeServer is started, port: " + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        stop = true;
    }

    @Override
    public void run() {
        try {
            while (!stop) {
                selector.select(1000);
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> it = keySet.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    try {
                        handlerInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlerInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //有可接受的事件
            if (key.isAcceptable()) {
                ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
                SocketChannel sc = socketChannel.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                //有可读事件
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer allocate = ByteBuffer.allocate(1024);
                int readBytes = sc.read(allocate);
                if (readBytes > 0) {
                    //
                    allocate.flip();
                    byte[] bytes = new byte[allocate.remaining()];
                    allocate.get(bytes);
                    String str = new String(bytes, "UTF-8");
                    System.out.println(str);
                } else if (readBytes < 0){
                    //sc.read()返回-1，链路已经关闭，需要关闭serverChannal释放资源
                    key.cancel();
                    sc.close();
                } else {}
                String currentTime = new Date().toString();
                doWrite(sc, currentTime);
            } else if (key.isWritable()) {

            }
        }
    }

    private void doWrite(SocketChannel sc, String resp) throws IOException {
        if (resp != null && resp.length() > 0) {
            byte[] bytes = resp.getBytes();
            ByteBuffer allocate = ByteBuffer.allocate(bytes.length);
            allocate.put(bytes);
            allocate.flip();
            sc.write(allocate);
        }
    }
}
