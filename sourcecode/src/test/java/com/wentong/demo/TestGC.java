package com.wentong.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestGC {
    public static void main(String[] args) throws Exception {
        TimeUnit.SECONDS.sleep(30);
        System.out.println("ready to start");
        List<GCDataObject> list = new ArrayList<>();
        for (int i = 0; i < 51200; i++) {
            list.add(new GCDataObject(2));
        }
        // 第一次 full gc
        System.gc();
        list.size();
        list = null;
        System.out.println("System.gc() 后");
        TimeUnit.SECONDS.sleep(5);
        List<GCDataObject> list1 = new ArrayList<>();
        for (int j = 0; j < 3200; j++) {
            list1.add(new GCDataObject(5));
        }
        list1.size();
        list1 = null;
        TimeUnit.SECONDS.sleep(10);

    }

    static class GCDataObject {
        byte[] bytes = null;
        RefObject refObject = null;

        public GCDataObject(int factor) {
            bytes = new byte[factor * 1024];
            refObject = new RefObject();
        }
    }

    static class RefObject {
        RefChildObject refChildObject = null;

        public RefObject() {
            refChildObject = new RefChildObject();
        }
    }

    static class RefChildObject {
        public RefChildObject() {
        }
    }
}
