package com.finup.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 贪心算法，追求局部最优化
 */
public class Greedy {
    static int[] weights = {35, 30, 60, 50, 40, 10, 25};
    static int[] weightCopy = {35, 30, 60, 50, 40, 10, 25};
    static int[] values = {10, 40, 30, 50, 35, 40, 30};
    static int maxWeight = 150;


    /**
     * 按照质量最轻的，还可以按照价值最大的，或者按照价值密度最大的。基本实现方式是一致的。
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        Arrays.sort(weights);
        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];
            if (weight != -1) {
                if (weightCount(list) + weight > maxWeight) {
                    weights[i] = -1;
                } else if (weightCount(list) + weight == maxWeight) {
                    list.add(weight);
                    weights[i] = -1;
                    break;
                } else {
                    list.add(weight);
                    weights[i] = -1;
                }
            }
        }
        System.out.println(list);
        calcValue(list);
    }

    private static int weightCount(List<Integer> list) {
        int max = 0;
        for (Integer integer : list) {
            max += integer;
        }
        return max;
    }

    private static void calcValue(List<Integer> list) {
        int valueCount = 0;
        for (Integer integer : list) {
            for (int i1 = 0; i1 < weightCopy.length; i1++) {
                if (weightCopy[i1] == integer) {
                    valueCount += values[i1];
                }
            }
        }
        System.out.println(valueCount);
    }

}
