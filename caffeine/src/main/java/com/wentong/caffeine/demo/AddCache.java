package com.wentong.caffeine.demo;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class AddCache {
    public static void main(String[] args) throws Exception {
        Cache<String, Person> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(10_000)
                .build();

        // 查找一个缓存元素， 没有查找到的时候返回null
        String name = "Jack";
        Person someone = cache.getIfPresent(name);
        // 查找缓存，如果缓存不存在则生成缓存元素,  如果无法生成则返回null
        someone = cache.get(name, k -> createExpensiveGraph(name));
        // 添加或者更新一个缓存元素
        cache.put(name, someone);
        System.out.println(cache.getIfPresent(name));
        TimeUnit.SECONDS.sleep(11);
        System.out.println(cache.getIfPresent(name));
        // 移除一个缓存元素
        cache.invalidate(name);
    }

    private static Person createExpensiveGraph(String name) {
        return new Person(name, 10, "北京");
    }

    static class Person {
        String name;
        int age;
        String address;

        public Person(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

}
