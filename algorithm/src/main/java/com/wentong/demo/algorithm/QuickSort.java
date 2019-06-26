package com.wentong.demo.algorithm;

/**
 * 对数组快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 10, 4, 6, -1, 0};
        printArray(arr);
        quickSort(arr, 0, arr.length);
        printArray(arr);
    }

    private static void quickSort(int[] arr,int from,int end) {
        if (from < end) {
            int mid = (end - from) / 2;
            quickSort(arr, from, mid);
            quickSort(arr, mid + 1, end);
        }
    }

    private static void printArray(int[] arr) {
        for (int o : arr) {
            System.out.print(o + " ");
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

}
