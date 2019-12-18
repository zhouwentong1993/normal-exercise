package com.wentong.netty;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServerNotZeroCopy {
    public static void main(String[] args) throws Exception {

        try (ServerSocket serverSocket = new ServerSocket(8999)) {
            while (true) {

                Socket socket = serverSocket.accept();

                InputStream inputStream = new DataInputStream(socket.getInputStream());
                byte[] buff = new byte[4096];

                while (inputStream.read(buff) != -1) {

                }
                System.out.println("read ok");
            }


//            OutputStream outputStream = socket.getOutputStream();
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//            try (FileInputStream fileInputStream = new FileInputStream("1.pdf")) {
//                byte[] bytes = new byte[4096];
//                while (fileInputStream.read(bytes) != -1) {
//                    objectOutputStream.write(bytes);
//                }
//            }

        }
    }
}
