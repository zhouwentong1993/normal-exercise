package com.wentong.nio;

import java.nio.channels.Selector;
import java.util.concurrent.TimeUnit;

public class SelectorTest2 {
    public static void main(String[] args) throws Exception {
        try (Selector selector = Selector.open()) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    selector.wakeup();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            selector.select();
            System.out.println("解开了");
        }

    }
}
