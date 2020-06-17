package com.rick.socket.bio;

import com.rick.socket.TimeServerHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞IO服务器
 * 记录：BufferedReader的readLine()方法，到最后会一直阻塞，直到出现异常或客户端关闭socket，才会返回null
 * 而在while循环里面打印消息后，立马把响应客户端，使客户端输入流读取数据，避免一直阻塞，然后执行socket.closed()操作。
 */
public class TimeServer {

    private static int port = 8080;

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            Socket socket;
            while (true) {
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }

        } catch (Exception e) {

        } finally {
            if (server != null) {
                try {
                    System.out.println("server is closed");
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                server = null;
            }
        }

    }

}
