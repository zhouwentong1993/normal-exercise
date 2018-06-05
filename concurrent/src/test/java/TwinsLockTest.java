import com.finup.demo.TwinsLock;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhouwentong on 2018/6/5.
 */
public class TwinsLockTest {

    @Test
    public void testTwinsLock() throws Exception {
            Lock lock = new TwinsLock();
        final class Worker extends Thread {

            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        TwinsLockTest.sleep(1);
                        System.out.println("Thread Name :" + Thread.currentThread().getName());
                        TwinsLockTest.sleep(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            Thread worker = new Thread(new Worker(),"test" + i);
            worker.setDaemon(true);
            worker.start();
        }

        for (int i = 0; i < 10; i++) {
            sleep(1);
            System.out.println();
        }

    }


    static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
