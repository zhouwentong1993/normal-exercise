package com.wentong.demo.jvm;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 * 年轻代 gc
 *
 * [GC (Allocation Failure) [DefNew: 7151K->353K(9216K), 0.0046394 secs] 7151K->6497K(19456K), 0.0046685 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * Heap
 *  def new generation   total 9216K, used 4531K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   eden space 8192K,  51% used [0x00000007bec00000, 0x00000007bf014930, 0x00000007bf400000)
 *   from space 1024K,  34% used [0x00000007bf500000, 0x00000007bf558508, 0x00000007bf600000)
 *   to   space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
 *  tenured generation   total 10240K, used 6144K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *    the space 10240K,  60% used [0x00000007bf600000, 0x00000007bfc00030, 0x00000007bfc00200, 0x00000007c0000000)
 *  Metaspace       used 2668K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 287K, capacity 386K, committed 512K, reserved 1048576K
 */
public class MinorGC {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
        byte[] b1, b2, b3, b4;
        b1 = new byte[2 * _1M];
        b2 = new byte[2 * _1M];
        b3 = new byte[2 * _1M];
        b4 = new byte[1 * _1M];
        b4 = new byte[1 * _1M];
    }

}
