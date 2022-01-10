package com.wentong.demo.network.shortconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 短连接代码示例
 */
public class ShortConnection {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(8081)) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            StringBuilder body = new StringBuilder();
            while (true) {
                String s = in.readLine();
                System.out.println("received:" + s + " at:" + System.currentTimeMillis());
                if (s == null) {
                    break;
                } else {
                    body.append(s);
                }
            }
            System.out.println(body);
            out.println(body);
            out.flush();
        }
    }

}