package com.wentong.demo.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFileUseIO {
    public static void main(String[] args) throws Exception{
//        File file = new File("/ReadFileUseIO.java");
        File file = new File("/Users/finup123/IdeaProjects/exercise/sourcecode/src/test/java/com/wentong/demo/nio/ReadFileUseIO.java");
//        readByBufferedReader(file);
//        readByFileReader(file);
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel readChannel = fileInputStream.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        // 将数据读入到缓冲区中，但是如果文件太大怎么办
        readChannel.read(allocate);

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/finup123/IdeaProjects/exercise/sourcecode/src/test/java/com/wentong/demo/nio/ReadFileUseIO_copy.txt");
        FileChannel outChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String message = "Hello NIO,Welcome,欢迎你12";
        byte[] bytes = message.getBytes();
        for (byte aByte : bytes) {
            System.out.println(byteBuffer.capacity());
            System.out.println(byteBuffer.position());
            System.out.println(byteBuffer.limit());
            byteBuffer.clear();
            byteBuffer.put(aByte);
            byteBuffer.flip();
            outChannel.write(byteBuffer);

        }

    }

    private static void readByFileReader(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        int i;
        while ((i = fileReader.read()) != -1) {
            System.out.print((char) i);
        }
    }

    private static void readByBufferedReader(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str;
        while ((str = (bufferedReader.readLine())) != null) {
            System.out.println(str);
        }
    }
}
