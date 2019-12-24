package com.wentong.ali;

public class ThreadField implements Runnable {
    private int x;
    private int y;

    public static void main(String[] args) {
        ThreadField threadField = new ThreadField();
        (new Thread(threadField)).start();
        (new Thread(threadField)).start();
    }

    @Override
    public void run() {
        x = x >= 10 ? (x + y) : x;
        y = y > 2 ? y * x : y;
        System.out.println("x=" + x++ + "y=" + ++y);
    }
}
