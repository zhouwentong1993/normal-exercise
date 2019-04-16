package com.wentong.demo.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IndependentTest {
    private List<String> list = new ArrayList<>();

    @Test
    public void test1() {
        list.add("a");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void test2() {
        list.add("b");
        list.add("c");
        Assert.assertEquals(3, list.size());
    }

}
