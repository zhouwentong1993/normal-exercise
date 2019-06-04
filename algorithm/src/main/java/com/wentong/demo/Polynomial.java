package com.wentong.demo;

/**
 * 多项式相加算法
 */
public class Polynomial {

    public static void main(String[] args) {
        double[] as = new double[10];
        for (int i = 0; i < 10; i++) {
            as[i] = 5;
        }
        polynomialAlgorithm(9, as, 1.1);

    }

    private static void polynomialAlgorithm(int n, double[] a, double x) {
        double result = 0;
        for (int i = n; i > 0; i--) {
            result = result + a[i] * x + a[i - 1];
        }
        System.out.println(result);
    }

}
