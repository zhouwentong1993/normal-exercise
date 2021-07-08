package com.wentong.demo.jni;

// 调用 jni demo
public class DataSynchronizer {

    /*
     * 加载本地底层C实现库
     */
    static {
        System.loadLibrary("synchronizer");
    }

    /**
     * 底层数据同步方法
     */
    private native String syncData(String status);

    /**
     * 程序启动，调用底层数据同步方法
     */
    public static void main(String... args) {
        String rst = new DataSynchronizer().syncData("ProcessStep2");
        System.out.println("The execute result from C is : " + rst);
    }

}
