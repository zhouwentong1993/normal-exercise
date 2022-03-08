package com.wentong.demo.network.shortconnection;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NoBindPort {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            int localPort = serverSocket.getLocalPort();
            System.out.println();
            Socket socket = serverSocket.accept();
            socket.setKeepAlive(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String s = in.readLine();
                System.out.println("received:" + s + " at:" + System.currentTimeMillis());
                out.println(StringUtils.reverse(s));
            }
        }

    }



}
