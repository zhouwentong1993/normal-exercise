package com.wentong.concurrent;

/**
 * Classfile /Users/finup123/IdeaProjects/exercise/interview/src/main/java/com/wentong/concurrent/SynchronizedTest.class
 *   Last modified 2019-11-11; size 782 bytes
 *   MD5 checksum 56a14dcf50189957386f2bf89ea199db
 *   Compiled from "SynchronizedTest.java"
 * public class com.wentong.concurrent.SynchronizedTest
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #7.#25         // java/lang/Object."<init>":()V
 *    #2 = Fieldref           #8.#26         // com/wentong/concurrent/SynchronizedTest.lock:Ljava/lang/Object;
 *    #3 = Fieldref           #27.#28        // java/lang/System.out:Ljava/io/PrintStream;
 *    #4 = String             #29            // lock block
 *    #5 = Methodref          #30.#31        // java/io/PrintStream.println:(Ljava/lang/String;)V
 *    #6 = String             #32            // lock method
 *    #7 = Class              #33            // java/lang/Object
 *    #8 = Class              #34            // com/wentong/concurrent/SynchronizedTest
 *    #9 = Utf8               lock
 *   #10 = Utf8               Ljava/lang/Object;
 *   #11 = Utf8               <init>
 *   #12 = Utf8               ()V
 *   #13 = Utf8               Code
 *   #14 = Utf8               LineNumberTable
 *   #15 = Utf8               main
 *   #16 = Utf8               ([Ljava/lang/String;)V
 *   #17 = Utf8               StackMapTable
 *   #18 = Class              #35            // "[Ljava/lang/String;"
 *   #19 = Class              #33            // java/lang/Object
 *   #20 = Class              #36            // java/lang/Throwable
 *   #21 = Utf8               lockMethod
 *   #22 = Utf8               <clinit>
 *   #23 = Utf8               SourceFile
 *   #24 = Utf8               SynchronizedTest.java
 *   #25 = NameAndType        #11:#12        // "<init>":()V
 *   #26 = NameAndType        #9:#10         // lock:Ljava/lang/Object;
 *   #27 = Class              #37            // java/lang/System
 *   #28 = NameAndType        #38:#39        // out:Ljava/io/PrintStream;
 *   #29 = Utf8               lock block
 *   #30 = Class              #40            // java/io/PrintStream
 *   #31 = NameAndType        #41:#42        // println:(Ljava/lang/String;)V
 *   #32 = Utf8               lock method
 *   #33 = Utf8               java/lang/Object
 *   #34 = Utf8               com/wentong/concurrent/SynchronizedTest
 *   #35 = Utf8               [Ljava/lang/String;
 *   #36 = Utf8               java/lang/Throwable
 *   #37 = Utf8               java/lang/System
 *   #38 = Utf8               out
 *   #39 = Utf8               Ljava/io/PrintStream;
 *   #40 = Utf8               java/io/PrintStream
 *   #41 = Utf8               println
 *   #42 = Utf8               (Ljava/lang/String;)V
 * {
 *   public com.wentong.concurrent.SynchronizedTest();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 3: 0
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=3, args_size=1
 *          0: getstatic     #2                  // Field lock:Ljava/lang/Object;
 *          3: dup
 *          4: astore_1
 *          5: monitorenter
 *          6: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *          9: ldc           #4                  // String lock block
 *         11: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *         14: aload_1
 *         15: monitorexit
 *         16: goto          24
 *         19: astore_2
 *         20: aload_1
 *         21: monitorexit
 *         22: aload_2
 *         23: athrow
 *         24: return
 *       Exception table:
 *          from    to  target type
 *              6    16    19   any
 *             19    22    19   any
 *       LineNumberTable:
 *         line 8: 0
 *         line 9: 6
 *         line 10: 14
 *         line 11: 24
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 255 /* full_frame
 offset_delta=19
         *locals=[class "[Ljava/lang/String;",

class java/lang/Object]
        *stack=[

class java/lang/Throwable]
        *frame_type=250 /* chop
        *offset_delta=4
        *
        *public static synchronized void lockMethod();
        *descriptor:()V
        *flags:ACC_PUBLIC,ACC_STATIC,ACC_SYNCHRONIZED
        *Code:
        *stack=2,locals=0,args_size=0
        *0:getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
        *3:ldc           #6                  // String lock method
        *5:invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        *8:return
        *LineNumberTable:
        *line 14:0
        *line 15:8
        *
        *static {};
        *descriptor:()V
        *flags:ACC_STATIC
        *Code:
        *stack=2,locals=0,args_size=0
        *0:new           #7                  // class java/lang/Object
        *3:dup
        *4:invokespecial #1                  // Method java/lang/Object."<init>":()V
        *7:putstatic     #2                  // Field lock:Ljava/lang/Object;
        *10:return
        *LineNumberTable:
        *line 5:0
        *}
        *SourceFile:"SynchronizedTest.java"
 */
public class SynchronizedTest {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            System.out.println("lock block");
        }
    }

    public static synchronized void lockMethod() {
        System.out.println("lock method");
    }

}
