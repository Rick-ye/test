package com.rick.socket.nio;

import java.io.IOException;

public class TimeServer {

    private static int port = 8080;

    public static void main(String[] args) {
        try {
            MultiplexerTimeServer server = new MultiplexerTimeServer(port);
            new Thread(server, "MultiplexerTimeServerThread").start();
        } catch (Exception e) {

        }

    }
}
