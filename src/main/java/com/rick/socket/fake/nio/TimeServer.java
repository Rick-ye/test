package com.rick.socket.fake.nio;

import com.rick.socket.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    private static int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
            TimeServerHandlerThreadPool handler = new TimeServerHandlerThreadPool(20, 30);
            Socket socket;
            while (true) {
                socket = server.accept();
                handler.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                server.close();
                server = null;
            }
        }
    }


}


