package com.wentong.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestScan {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        for (int i = 10000; i < 1000000; i++) {
            jedis.set(String.valueOf(i), String.valueOf(i));
        }
        List<String> dupKeys = new ArrayList<>();
        Set<String> keys = new HashSet<>();
        ScanResult<String> scan = jedis.scan("0");
        String cursor = scan.getCursor();
        dupKeys.addAll(scan.getResult());
        keys.addAll(scan.getResult());
        while (!cursor.equals("0")) {
            ScanResult<String> scan1 = jedis.scan(cursor);
            cursor = scan1.getCursor();
            dupKeys.addAll(scan1.getResult());
            keys.addAll(scan1.getResult());
        }
        System.out.println(dupKeys.size());
        System.out.println(keys.size());

    }
}
