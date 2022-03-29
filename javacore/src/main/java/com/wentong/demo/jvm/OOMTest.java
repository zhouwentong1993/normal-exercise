package com.wentong.demo.jvm;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    public static void main(String[] args) throws Exception {
        long counter = 0;
        List<Person> list = new ArrayList<>();
        while (true) {
            list.add(new Person());
            System.out.println("current count is: " + (++counter));
        }
    }

}
