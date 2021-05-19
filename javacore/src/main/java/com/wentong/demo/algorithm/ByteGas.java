package com.wentong.demo.algorithm;

public class ByteGas {

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        int index = getIndex(gas, cost);
        System.out.println(index);

    }

    private static int getIndex(int[] gas, int[] cost) {
        int length = gas.length;
        int[] sub = new int[length];
        for (int i = 0; i < length; i++) {
            sub[i] = gas[i] - cost[i];
        }
        int index = -1;
        for (int i = 0; i < length; i++) {
            if (sub[i] > 0) {
                index = i;
                int sum = sub[index];
                for (int j = i+1; j < i + length; j++) {
                    sum += sub[j % length];
                    if (sum < 0) {
                        index = -1;
                        break;
                    }
                    System.out.println(sum);
                }
                if (sum >= 0) {
                    return index;
                }
            }
        }
        return index;
    }

}
