package com.wentong.demo.jvm;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class MyClassLoader extends ClassLoader {

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public MyClassLoader() {
    }

    @Override
    protected Class<?> findClass(String name) {
        File file = new File("/Users/finup123/IdeaProjects/exercise/javacore/src/main/java/com/wentong/demo/jvm/Person.class");
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (WritableByteChannel wbc = Channels.newChannel(baos)) {
            ByteBuffer by = ByteBuffer.allocate(1024);

            while (true) {
                int i = 0;
                try {
                    i = fc.read(by);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (i == 0 || i == -1) {
                    break;
                }
                by.flip();
                try {
                    wbc.write(by);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                by.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        Class<?> aClass = this.defineClass(name, bytes, 0, bytes.length);
        return aClass;

    }
}
