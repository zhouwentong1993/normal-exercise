package com.wentong.demo;

import org.openjdk.jol.info.ClassLayout;

/**
 * https://juejin.im/post/5dfddb73f265da33d912e412
 * 对象头分为两部分：运行时数据和类型指针。
 * 运行时数据就是存储的对象头，hashcode 这些。固定长度，在 64 位机器上 占用 8 byte。
 * 类型指针大小取决于变量大小，对于 int 来说，占用 4 byte，其它原生类型也有各自的大小，引用类型占 8 byte，如果开启了
 * 指针压缩，那么占据 4 字节。数组占据大小取决于类型，同原生类型，但还需要一个 int 来记录数组长度，故需要多加 4 byte
 */

/**
 * 开启指针压缩数据
 * java.lang.String object internals:
 *  OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
 *       0    12          (object header)                           N/A
 *      12     4   char[] String.value                              N/A
 *      16     4      int String.hash                               N/A
 *      20     4          (loss due to the next object alignment)
 * Instance size: 24 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * com.wentong.demo.Animal object internals:
 *  OFFSET  SIZE    TYPE DESCRIPTION                               VALUE
 *       0    12         (object header)                           N/A
 *      12     4     int Animal.age                                N/A
 *      16     4   int[] Animal.a                                  N/A
 *      20     4         (loss due to the next object alignment)
 * Instance size: 24 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * 关闭指针压缩数据：
 *java.lang.String object internals:
 *  OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
 *       0    16          (object header)                           N/A
 *      16     8   char[] String.value                              N/A
 *      24     4      int String.hash                               N/A
 *      28     4          (loss due to the next object alignment)
 * Instance size: 32 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * com.wentong.demo.Animal object internals:
 *  OFFSET  SIZE    TYPE DESCRIPTION                               VALUE
 *       0    16         (object header)                           N/A
 *      16     4     int Animal.age                                N/A
 *      20     4         (alignment/padding gap)
 *      24     8   int[] Animal.a                                  N/A
 * Instance size: 32 bytes
 * Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
 */
public class JOL {
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(String.class).toPrintable());
        System.out.println(ClassLayout.parseClass(Animal.class).toPrintable());
    }
}

class Animal {
    int age;
    int[] a;
//    Object object;
}
