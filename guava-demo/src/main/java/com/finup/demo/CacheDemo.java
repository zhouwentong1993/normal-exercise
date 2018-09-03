package com.finup.demo;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CacheDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheDemo.class);

    @Test
    public void testCache() throws Exception {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(100, TimeUnit.SECONDS)
                .removalListener(notification -> {
                    System.out.println("removed");
                });

        Cache<Object, Object> build = cacheBuilder.build();
        build.put("key1", "value1");
    }

    private static LoadingCache<Integer, Employee> empCache;

    static {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                // 如果超出 maximumSize 限制，回调该方法，lru
                .removalListener(notification -> System.out.println("Removed key:" + notification.getKey() + " --> value:" + notification.getValue() + "，cause：" + notification.getCause()))
                .recordStats();
        empCache = cacheBuilder.build(new CacheLoader<Integer, Employee>() {
            @Override
            public Employee load(Integer key) {
                return getEmployeeById(key);
            }
        });
    }


    @Test
    public void test1() throws Exception {
        CacheBuilder.newBuilder()
                .removalListener(notification -> {
                })
                .expireAfterAccess(1, TimeUnit.SECONDS).maximumSize(1000).build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key + new Date();
            }
        });

        Employee employee = empCache.get(1);
        Employee employee1 = empCache.get(2);
//        TimeUnit.SECONDS.sleep(11);
        Employee employee2 = empCache.get(3);
        System.out.println(employee);
        System.out.println(employee1);
        System.out.println(employee2);
    }

    private static Employee getEmployeeById(long id) {
        System.out.println("CacheDemo.getEmployeeById");
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setUsername("em1");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setUsername("em2");

        Employee employee3 = new Employee();
        employee3.setId(2L);
        employee3.setUsername("em3");
        if (id == 1) {
            return employee1;
        } else if (id == 2) {
            return employee2;
        } else if (id == 3) {
            return employee3;
        } else {
            return null;
        }
    }
}
