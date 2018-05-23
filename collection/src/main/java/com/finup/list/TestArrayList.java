package com.finup.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouwentong on 2018/5/18.
 */
public class TestArrayList {


    /**
     * 主要是测试 ArrayList 里面的 elementData 变化。
     * 还有就是 trimToSize 方法。
     */
    @Test
    public void testTrimToSize() {
        ArrayList<String> list = new ArrayList<>();
        list.add(null);
        list.add("aa");
        list.add("bb");
        list.add(null);
        list.add(null);
        list.add("dd");
        System.out.println(list.size());

        list.trimToSize();
        System.out.println(list.size());
        list.add("aaa");
        System.out.println(list.size());
    }

    @Test
    public void testArrayEquals() throws Exception {
        Object[] arr1 = {"aa", "bb"};
        Object[] arr2 = {"aa", "bb"};
        System.out.println(arr1 == arr2);
        Object[] arr3 = {};
        Object[] arr4 = {};
        System.out.println(arr3 == arr4);
    }

    @Test
    public void testNewArrayList() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        List<String> list = new ArrayList<>();
        System.out.println(list);
        list.add("aaa");
        System.out.println(list);
    }

    @Test
    public void testMove() {
        System.out.println(3 >> 1);
        int oldCapacity = 100;

        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);

        System.out.println(-2 >> 1);
    }

    @Test
    public void testToArray() throws Exception {
//        TimeUnit.SECONDS.sleep(5);
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        String[] arr = new String[]{"adf", null, null};
        list.toArray(arr);
        for (String s : arr) {
            System.out.println(s);
        }
    }

    @Test
    public void testAddWithIndex() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        list.add(0, "w");
        System.out.println(list);

    }

    @Test
    public void testRemove() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(2);
        System.out.println(list);
    }

    @Test
    public void testAddAll() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.addAll(2, list);
        System.out.println(list);

    }

    @Test
    public void testContains() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        System.out.println(list.contains(new Object()));

    }

    @Test
    public void testRemoveAll() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("5");
        list.add("5");
        list.add("5");
        list.add("5");
        list.add("5");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list.removeAll(list2);
        System.out.println(list);
        System.out.println();
    }

    @Test
    public void testRemove1() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("e");
        list.add("h");
        list.add("c");
        System.out.println(batchRemove(list, true));
    }

    private boolean batchRemove(Collection<?> c, boolean complement) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");

        Object[] objects = list.toArray();
        int size = objects.length;
        // 复制一个本地变量，命名有讲究吗？为啥用的是跟之前一样的名字。
        final Object[] elementData = objects;
        // 这个应该是有讲究，r -> read,w -> write
        int r = 0, w = 0;
        boolean modified = false;
        for (; r < size; r++) {
            if (c.contains(elementData[r]) == complement) {
                // 通过遍历，把当前元素的值放到数组的头部，最后再次清空从 0 到 w 的元素就行
                System.out.println("w = " + w);
                elementData[w++] = elementData[r];
                print(elementData);
            }
        }

        if (w != size) {
            // clear to let GC do its work
            for (int i = w; i < size; i++) {
                elementData[i] = null;
            }
        }
        for (Object elementDatum : elementData) {
            System.out.println(elementDatum + " ");
        }
        return modified;
    }

    private void print(Object[] arr) {
        for (Object elementDatum : arr) {
            System.out.print(elementDatum + " ");
        }
        System.out.println();
    }

}
