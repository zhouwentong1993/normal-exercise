package com.wentong.demo.network.shortconnection;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 本来是用来测试长连接、短连接的，但是发现长连接和短连接的区别只是在是否及时关闭而已，所以无所谓了。
 */
public class BioConnection {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(8081)) {
            Socket socket = serverSocket.accept();
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