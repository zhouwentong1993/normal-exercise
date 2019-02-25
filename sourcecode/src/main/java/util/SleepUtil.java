package util;

import java.util.concurrent.TimeUnit;

public class SleepUtil {
    private SleepUtil(){}

    public static void SleepOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
