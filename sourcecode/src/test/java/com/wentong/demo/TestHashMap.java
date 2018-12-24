package com.wentong.demo;

import org.junit.Test;

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
