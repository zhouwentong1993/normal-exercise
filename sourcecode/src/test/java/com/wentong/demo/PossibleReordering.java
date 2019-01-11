package com.wentong.demo;

/**
 * 第463764次(0,1)
 * 第463766次(0,1)
 * 第463768次(0,1)
 * 第463770次(0,1)
 * 第463772次(0,1)
 * 第463774次(0,1)
 * 第463776次(0,1)
 * 第463778次(0,1)
 * 第463780次(0,1)
 * 第463782次(0,0)
 * =================
 * 第4785702次(0,1)
 * 第4785704次(0,1)
 * 第4785706次(0,1)
 * 第4785708次(0,1)
 * 第4785710次(0,1)
 * 第4785712次(0,1)
 * 第4785714次(0,1)
 * 第4785716次(0,0)
 *
 *
 * 经过好多次才出现
 */
public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000000; i++) {
            Thread one = new Thread(new Runnable() {
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread other = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();
            other.start();
            one.join();
            other.join();
            if (x == 0 && y == 0) {
                System.err.println("第"+i ++ +"次(" + x + "," + y + ")");
                System.exit(0);
            } else {
                System.out.println("第"+i ++ +"次(" + x + "," + y + ")");
                x = 0;
                y = 0;
                a = 0;
                b = 0;
            }
        }
    }
}
