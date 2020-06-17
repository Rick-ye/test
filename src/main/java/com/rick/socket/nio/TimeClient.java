package com.rick.socket.nio;

/**
 * nio client
 */
public class TimeClient {

    private static int PORT = 8080;

    public static void main(String[] args) {
        new Thread(new TimeClientHandler("127.0.0.1", PORT)).start();
    }
}
