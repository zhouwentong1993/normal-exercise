package core;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.TimeUnit;

public class FastThreadLocalTest {

    private static final FastThreadLocal<Integer> THREAD_LOCAL = new FastThreadLocal<>();

    public static void main(String[] args) throws Exception {

        // FastThreadLocal 的使用与 ThreadLocal 无异，能够兼容。
        // 只不过 FastThreadLocal 需要同 FastThreadLocalThread 搭配使用效率才高。
        FastThreadLocalThread fast1 = new FastThreadLocalThread(() -> {
            try {
                TimeUnit.NANOSECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                THREAD_LOCAL.set(i);
                System.out.printf("Thread:%s get key:%s%n", Thread.currentThread().getName(), THREAD_LOCAL.get());
            }
        }, "Fast1");
        fast1.start();


        FastThreadLocalThread fast2 = new FastThreadLocalThread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.printf("Thread:%s get key:%s%n", Thread.currentThread().getName(), THREAD_LOCAL.get());
            }
        }, "Fast2");
        fast2.start();
        fast1.join();
        fast2.join();
    }
}
