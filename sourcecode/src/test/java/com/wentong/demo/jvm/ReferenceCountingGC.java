package com.wentong.demo.jvm;

public class ReferenceCountingGC {

    public Object instance = null;
    private int _1MB = 1024 * 1024;

    private byte[] arr = new byte[2 * _1MB];

    private void testGc() {
        ReferenceCountingGC referenceCountingGC1 = new ReferenceCountingGC();
        ReferenceCountingGC referenceCountingGC2 = new ReferenceCountingGC();
        referenceCountingGC1.instance = referenceCountingGC2;
        referenceCountingGC2.instance = referenceCountingGC1;

        referenceCountingGC1 = null;
        referenceCountingGC2 = null;

        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountingGC referenceCountingGC = new ReferenceCountingGC();
        referenceCountingGC.testGc();

    }




}
