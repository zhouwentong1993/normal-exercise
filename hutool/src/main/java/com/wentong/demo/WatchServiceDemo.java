package com.wentong.demo;

import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 监控文件工具，可以监控增删改事件
 * 发现有点儿延迟
 */
public class WatchServiceDemo {
    public static void main(String[] args) {
        File file = new File("/Users/finup123/Desktop/测试.txt");
        WatchMonitor watchMonitor = WatchMonitor.create(file, WatchMonitor.ENTRY_MODIFY);
        watchMonitor.setWatcher(new Watcher() {
            @Override
            public void onCreate(WatchEvent<?> event, Path currentPath) {
                System.out.println("WatchServiceDemo.onCreate");
            }

            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                System.out.println("WatchServiceDemo.onModify");
            }

            @Override
            public void onDelete(WatchEvent<?> event, Path currentPath) {
                System.out.println("WatchServiceDemo.onDelete");
            }

            @Override
            public void onOverflow(WatchEvent<?> event, Path currentPath) {
                System.out.println("WatchServiceDemo.onOverflow");
            }
        });
        watchMonitor.start();
    }
}
