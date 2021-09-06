package com.wentong.demo.java8inaction;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + apply(quote.getPrice(), quote.getCode());
    }

    @SneakyThrows
    private static double apply(double price, Code code) {
        TimeUnit.SECONDS.sleep(1);
        return price * (100 - code.percentage) / 100;
    }
}
