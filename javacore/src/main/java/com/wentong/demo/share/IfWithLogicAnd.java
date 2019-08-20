package com.wentong.demo.share;

import org.junit.Test;

public class IfWithLogicAnd {

    @Test
    public void logicAnd() {

        if (action1() && action2() || action3()) {

        }
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
