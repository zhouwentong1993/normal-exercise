package com.wentong.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class MapOrStringTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.address = "address" + i;
            user.company = "company" + i;
            user.firstName = "firstName" + i;
            user.lastName = "lastName" + i;
            user.school = "school" + i;
            jedis.lpush("test1", JSONObject.toJSONString(user));
        }
//        jedis.set("test", JSONObject.toJSONString(list));
        System.out.println("ok");
    }


    static class User {
        String firstName;
        String address;
        String lastName;
        String school;
        String company;
    }

}
