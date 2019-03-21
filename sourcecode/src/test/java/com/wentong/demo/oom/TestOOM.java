package com.wentong.demo.oom;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestOOM {

    /**
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    @Test
    public void heapOOM() {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }

    }
}
