package com.wentong.demo.jvm;


/**
 * 当指定晋升阈值为 1 时
 * [GC (Allocation Failure) [DefNew: 7446K->925K(9216K), 0.0060483 secs] 7446K->5021K(19456K), 0.0061169 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [DefNew: 5021K->0K(9216K), 0.0020517 secs] 9117K->4968K(19456K), 0.0020783 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
 * Heap
 *  def new generation   total 9216K, used 4342K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   eden space 8192K,  53% used [0x00000007bec00000, 0x00000007bf03d8a0, 0x00000007bf400000)
 *   from space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
 *   to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
 *  tenured generation   total 10240K, used 4968K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *    the space 10240K,  48% used [0x00000007bf600000, 0x00000007bfada398, 0x00000007bfada400, 0x00000007c0000000)
 *  Metaspace       used 3536K, capacity 4558K, committed 4864K, reserved 1056768K
 *   class space    used 391K, capacity 394K, committed 512K, reserved 1048576K
 *
 *   当指定晋升阈值为 15 时
 *   结论不符合啊 from & to 还都是 0% 啊
 *   [GC (Allocation Failure) [DefNew: 5359K->609K(9216K), 0.0033839 secs] 5359K->4705K(19456K), 0.0034140 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 * [GC (Allocation Failure) [DefNew: 4705K->0K(9216K), 0.0007515 secs] 8801K->4694K(19456K), 0.0007649 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * Heap
 *  def new generation   total 9216K, used 4178K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   eden space 8192K,  51% used [0x00000007bec00000, 0x00000007bf014930, 0x00000007bf400000)
 *   from space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
 *   to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
 *  tenured generation   total 10240K, used 4694K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *    the space 10240K,  45% used [0x00000007bf600000, 0x00000007bfa95980, 0x00000007bfa95a00, 0x00000007c0000000)
 *  Metaspace       used 2668K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 287K, capacity 386K, committed 512K, reserved 1048576K
 */
public class LongTimeObjectToOld {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
        byte[] b1, b2, b3;
        b1 = new byte[_1M / 4];
        b2 = new byte[4 * _1M];
        b3 = new byte[4 * _1M];
        b3 = null;
        b3 = new byte[4 * _1M];

    }

}
