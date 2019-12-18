package com.wentong.netty;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * 阻塞 io，非零拷贝
 * 发送字节数：25531541总耗时：65ms
 */
public class BioClientNotZeroCopy {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Socket socket = new Socket("localhost", 8999);
        InputStream dataInputStream = new DataInputStream(new FileInputStream("1.pdf"));
        byte[] buff = new byte[4096];
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        int count;
        int total = 0;
        while ((count = dataInputStream.read(buff)) != -1) {
            total += count;
            dataOutputStream.write(buff, 0, buff.length);
        }
        System.out.println("发送字节数：" + total + "总耗时：" + (System.currentTimeMillis() - start) + "ms");

    }
}
