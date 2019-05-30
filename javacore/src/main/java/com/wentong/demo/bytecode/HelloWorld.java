package com.wentong.demo.bytecode;

public class HelloWorld {

    /*
    通过 xxd HelloWorld.class 得到的结果
    00000000: cafe babe 0000 0034 001d 0a00 0600 0f09  .......4........
    00000010: 0010 0011 0800 120a 0013 0014 0700 1507  ................
    00000020: 0016 0100 063c 696e 6974 3e01 0003 2829  .....<init>...()
    00000030: 5601 0004 436f 6465 0100 0f4c 696e 654e  V...Code...LineN
    00000040: 756d 6265 7254 6162 6c65 0100 046d 6169  umberTable...mai
    00000050: 6e01 0016 285b 4c6a 6176 612f 6c61 6e67  n...([Ljava/lang
    00000060: 2f53 7472 696e 673b 2956 0100 0a53 6f75  /String;)V...Sou
    00000070: 7263 6546 696c 6501 000f 4865 6c6c 6f57  rceFile...HelloW
    00000080: 6f72 6c64 2e6a 6176 610c 0007 0008 0700  orld.java.......
    00000090: 170c 0018 0019 0100 0b68 656c 6c6f 2077  .........hello w
    000000a0: 6f72 6c64 0700 1a0c 001b 001c 0100 2463  orld..........$c
    000000b0: 6f6d 2f77 656e 746f 6e67 2f64 656d 6f2f  om/wentong/demo/
    000000c0: 6279 7465 636f 6465 2f48 656c 6c6f 576f  bytecode/HelloWo
    000000d0: 726c 6401 0010 6a61 7661 2f6c 616e 672f  rld...java/lang/
    000000e0: 4f62 6a65 6374 0100 106a 6176 612f 6c61  Object...java/la
    000000f0: 6e67 2f53 7973 7465 6d01 0003 6f75 7401  ng/System...out.
    00000100: 0015 4c6a 6176 612f 696f 2f50 7269 6e74  ..Ljava/io/Print
    00000110: 5374 7265 616d 3b01 0013 6a61 7661 2f69  Stream;...java/i
    00000120: 6f2f 5072 696e 7453 7472 6561 6d01 0007  o/PrintStream...
    00000130: 7072 696e 746c 6e01 0015 284c 6a61 7661  println...(Ljava
    00000140: 2f6c 616e 672f 5374 7269 6e67 3b29 5600  /lang/String;)V.
    00000150: 2100 0500 0600 0000 0000 0200 0100 0700  !...............
    00000160: 0800 0100 0900 0000 1d00 0100 0100 0000  ................
    00000170: 052a b700 01b1 0000 0001 000a 0000 0006  .*..............
    00000180: 0001 0000 0003 0009 000b 000c 0001 0009  ................
    00000190: 0000 0025 0002 0001 0000 0009 b200 0212  ...%............
    000001a0: 03b6 0004 b100 0000 0100 0a00 0000 0a00  ................
    000001b0: 0200 0000 0500 0800 0600 0100 0d00 0000  ................
    000001c0: 0200 0e                                  ...

     *
     */

    /*

    通过 javap -v 得到的数据，默认生成的构造方法和 main 这两个的 args_size 都是 1，因为非静态方法的第一个参数
    默认都是「this」，这个跟 Python 有点儿像。
     public com.wentong.demo.bytecode.HelloWorld();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String hello world
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 5: 0
        line 6: 8
}
SourceFile: "HelloWorld.java"

     */

    /*

    Javap -l HelloWorld 得到的结果，打印字节码行号和本地变量表！
    警告: 二进制文件HelloWorld包含com.wentong.demo.bytecode.HelloWorld
Compiled from "HelloWorld.java"
public class com.wentong.demo.bytecode.HelloWorld {
  public com.wentong.demo.bytecode.HelloWorld();
    LineNumberTable:
      line 3: 0

  public static void main(java.lang.String[]);
    LineNumberTable:
      line 5: 0
      line 6: 8

     */
    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
