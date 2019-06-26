package com.wentong.demo.algorithm;

/**
 * 警察抓了 A、B、C、D 四名罪犯，其中一名是小偷，审讯的时候：
 * <p>
 * A说：“我不是小偷。”    x !=0
 * B说：“C 是小偷。”     x = 2
 * C说：“小偷肯定是 D。”  x = 3
 * D说：“C 是在冤枉人。”  x != 3
 */
public class Exhaustivity {

    public static void main(String[] args) {

        for (int criminal = 0; criminal < 4; criminal++) {
            int resA = criminal != 0 ? 1 : 0;
            int resB = criminal == 2 ? 1 : 0;
            int resC = criminal == 3 ? 1 : 0;
            int resD = 1 - resC;

            if (resA + resB + resC + resD == 3) {
                System.out.println(criminal);
            }

        }
    }
}
