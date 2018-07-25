package com.finup.domain;

import java.io.Serializable;

/**
 * Created by zhouwentong on 2018/7/25.
 */
public class User implements Serializable{
    private String username;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("username", username)
                .add("age", age)
                .toString();
    }
}
