package com.wentong.demo;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestHashMap {

    @Test(expected = NullPointerException.class)
    public void testSet() {
        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        objectObjectHashtable.put(null, null);
    }

    @Test
    public void testMapResizeTreeMap() throws Exception{
        TimeUnit.SECONDS.sleep(10);
        Map<Integer,Foo> map = new HashMap<>(1);
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

    class Foo {

        @Override
        public boolean equals(Object o) {
            return true;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }



}
