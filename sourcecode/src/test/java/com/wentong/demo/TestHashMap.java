package com.wentong.demo;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestHashMap {

    @Test(expected = NullPointerException.class)
    public void testSet() {
        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        objectObjectHashtable.put(null, null);
    }

    @Test
    public void testConstruct() {
        Map<String, String> map = new HashMap<>();
        System.out.println(map);
    }

    @Test
    public void testMapResizeTreeMap() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        Map<Integer, Foo> map = new HashMap<>(1);
        for (int i = 0; i < 8; i++) {
            map.put(i, new Foo());
        }

        System.out.println(map.size());
    }

    int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }

    @Test
    public void testTableSize() {
        int i = tableSizeFor(5);
        Assert.assertEquals(i, 8);
    }

    @Test
    public void testInit() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Field threshold = map.getClass().getDeclaredField("threshold");
        String s = threshold.toString();
        System.out.println(s);
    }

    @Test
    public void testHash() {
        int i = new String("21k3jl12").hashCode();
        System.out.println(i);
    }

//    @Test
//    public void testPutIfAbsent() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("aaa", null);
//        map.putIfAbsent("aaa", "bbb");
//        map.putIfAbsent("aaa", "ccc");
//        Assert.assertEquals("bbb", map.get("aaa"));
//    }

    @Test
    public void testHashMapMaxSize() {
        Map<String, Object> map = new HashMap<>(1 << 30);
        for (int i = 0; i < (1 << 30) + 10; i++) {
            map.put(String.valueOf(i), i);
        }
        System.out.println(map.size());
        System.out.println(map.size() - (1 << 30));
    }

    @Test
    public void testRemove() {
        Map<String, Object> map = new HashMap<>();
        map.put("aaa", null);
        System.out.println(map.remove("aaa"));
        System.out.println(map);
    }

    @Test
    public void testMapMultiThread() throws Exception {
        Map<Foo, Foo> myMap = Collections.synchronizedMap(new HashMap<>());
        List<Thread> listOfThreads = new ArrayList<>();

        // Create 10 Threads
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {

                // Let Each thread insert 3000 Items
                for (int j = 0; j < 3000; j++) {
                    Foo key = new Foo();
                    myMap.put(key, key);
                }

            });
            thread.start();
            listOfThreads.add(thread);
        }

        for (Thread thread : listOfThreads) {
            thread.join();
        }
        System.out.println("Count should be 30000, actual is : " + myMap.size());
    }

    @Test
    public void testResize() throws Exception {
        TimeUnit.SECONDS.sleep(100);
        Map<Foo1, String> map = new HashMap<>(4);
        Foo1 key = new Foo1(1);
        map.put(key, "1");


        Foo1 key1 = new Foo1(5);
        map.put(key1, "5");

        Foo1 key2 = new Foo1(13);
        map.put(key2, "13");

        Foo1 key3 = new Foo1(29);
        map.put(key3, "29");

        map.put(new Foo1(2), "2");
        map.put(new Foo1(6), "6");
        System.out.println(map);
    }

    @Test
    public void testResize1() throws Exception {
        Map<Foo1, String> map = new HashMap<>(8);
        Foo1 key = new Foo1(1);
        map.put(key, "1");

        Foo1 key1 = new Foo1(9);
        map.put(key1, "9");

        Foo1 key2 = new Foo1(25);
        map.put(key2, "25");

        Foo1 key3 = new Foo1(17);
        map.put(key3, "17");

        map.put(new Foo1(2), "2");
        map.put(new Foo1(6), "6");
        System.out.println(map);
    }

    @Test
    public void testResize2() {
        int oldCap = 4;
        int newCap = 7;
        for (int i = 0; i < 10000; i++) {
            if ((i & oldCap + 4) == (i & newCap)) {
                System.out.println(i);
            }
        }
    }

    @Test
    public void testComputeIfAbsent() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
        map.put("e", "e");
        map.put("f", "f");
        System.out.println(map.computeIfAbsent("d", String::toUpperCase));
    }

    private static HashMap<Integer, String> map = new HashMap<>(2, 0.75f);

    @Test
    public void testHashMapInfiniteLoop() {

        System.out.println("java版本号：" + System.getProperty("java.version")); // java版本号"


        map.put(5, "C");

        new Thread("Thread1") {
            public void run() {
                map.put(7, "B");
                System.out.println(map);
            }

            ;
        }.start();
        new Thread("Thread2") {
            public void run() {
                map.put(3, "A");
                System.out.println(map);
            }

        }.start();
    }

    @Test
    public void testGuavaInitMap() {
        HashMap<String, Object> map = Maps.newHashMapWithExpectedSize(5);

    }


    /**
     * 当 put foo9 时，会调用 treeify 方法，但是实际不会变成树结构，因为 tab.length < 64，需要等再多两次 resize 才能到 64，也就是 put foo11 时，这时会将 list 转换成 TreeMap
     */
    @Test
    public void testTreeify() {
        Map<Foo1,String> map = new HashMap<>();
        Foo1 foo1 = new Foo1(1);
        Foo1 foo2 = new Foo1(1);
        Foo1 foo3 = new Foo1(1);
        Foo1 foo4 = new Foo1(1);
        Foo1 foo5 = new Foo1(1);
        Foo1 foo6 = new Foo1(1);
        Foo1 foo7 = new Foo1(1);
        Foo1 foo8 = new Foo1(1);
        Foo1 foo9 = new Foo1(1);
        Foo1 foo10 = new Foo1(1);
        Foo1 foo11 = new Foo1(1);
        Foo1 foo12 = new Foo1(1);
        map.put(foo1, "1");
        map.put(foo2, "2");
        map.put(foo3, "3");
        map.put(foo4, "4");
        map.put(foo5, "5");
        map.put(foo6, "6");
        map.put(foo7, "7");
        map.put(foo8, "8");
        map.put(foo9, "9");
        map.put(foo10, "10");
        map.put(foo11, "11");


        map.remove(foo1);
        map.remove(foo2);
        map.remove(foo3);
        map.remove(foo4);
        map.remove(foo5);
        map.remove(foo6);
        map.remove(foo7);
        map.remove(foo8);
        map.remove(foo9);
        map.remove(foo10);
        map.remove(foo11);
//        map.put(foo10, "10");
//        map.put(foo11, "11");
//        map.put(foo12, "12");
        System.out.println(map);
    }

    class Foo {

        int value = 3;

        @Override
        public int hashCode() {
            return 1;
        }
    }

    class Foo1 {
        int value;

        public Foo1(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value;
        }
    }


}
