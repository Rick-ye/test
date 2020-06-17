package com.rick.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TimeClient {

    private static int port = 8080;

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        OutputStreamWriter outputStreamWriter = null;
        PrintWriter writer = null;
        BufferedReader in = null;
        try {

            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("do u get my msg");
            String resp = in.readLine();
            System.out.println("resp: " + resp);
        } catch (Exception e) {

        } finally {
            if (writer != null) {
                writer.close();
            }
            if (in != null) {
                in.close();
            }
            if (socket != null) {
                socket.close();
                socket = null;
            }

        }
    }
}
