package com.wentong.demo.network.shortconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("127.0.0.1", 8081)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("hello world");
            System.out.println("send success:" + System.currentTimeMillis());
            String line = bufferedReader.readLine();
            System.out.println(line);
        }
    }

}
