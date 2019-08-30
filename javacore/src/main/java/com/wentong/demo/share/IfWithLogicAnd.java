package com.wentong.demo.share;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IfWithLogicAnd {

    @Test
    public void logicAnd() {

        if (action1() && action2() || action3()) {

        }
    }

    @Test
    public void testGuavaNull() {
        ImmutableList<Object> of = ImmutableList.of(null);
        System.out.println(of);

    }

    @Test
    public void testInt2Map() {
        int i = 10;
        List<Integer> list = new ArrayList<>();
        list.add(i);
    }

    private boolean action1() {
        return false;
    }

    private boolean action2() {
        return true;
    }

    private boolean action3() {
        return false;
    }
}
