package com.wentong.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestSplitList {
    public static void main(String[] args) {

        int maxIndex = 50000001;
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i <= maxIndex; i++) {
            ids.add(i);
        }
        List<Integer> subList;

        long startTime = System.currentTimeMillis();
        int pageIndex = 0, pageSize = 500;
        int pageCount = 0;
        while (!(subList = ids.stream().skip(pageSize * pageIndex++).limit(pageSize).parallel().collect(Collectors.toList())).isEmpty()) {
            //System.out.println("当前页[" + (pageIndex) + "]"+ subList);
            subList.size();
            pageCount++;
        }
        System.out.println("每次创建流 kill=" + (System.currentTimeMillis() - startTime) + "ms pageCount=" + pageCount);

        startTime = System.currentTimeMillis();
        int totalPageCount = (maxIndex + 1) / pageSize + ((maxIndex + 1) % pageSize == 0 ? 0 : 1);
        List<List<Integer>> resultPages = Stream
                .iterate(0, n -> n + 1)
                .limit(totalPageCount)
                .parallel()
                .map(index -> ids.stream().skip(index * pageSize).limit(pageSize).parallel().collect(Collectors.toList()))
                .collect(Collectors.toList());
        System.out.println("并发创建流 kill=" + (System.currentTimeMillis() - startTime) + "ms pageCount=" + resultPages.size());

        startTime = System.currentTimeMillis();
        int start = 0, end = 0;
        pageCount = 0;
        while (end < maxIndex) {
            end = Math.min(start + pageSize, maxIndex);
            subList = ids.subList(start, end);
            subList.size();
            pageCount++;
            start = Math.min(end, maxIndex);
        }
        System.out.println("陈旧到方法 kill=" + (System.currentTimeMillis() - startTime) + "ms pageCount=" + pageCount);
    }
}
