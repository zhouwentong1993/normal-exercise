package java8inaction;

import com.google.common.base.Stopwatch;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random();
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) throws Exception {
        Shop shopA = new Shop("shopA");
        Future<Double> future = shopA.getPriceAsyncV2("Banana");
        System.out.println(future.get());
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<CompletableFuture<String>> applesFuture = findPricesV2("Banana");
        List<String> collect = applesFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
        stopwatch.stop();
        System.out.println(stopwatch);
        System.out.println(applesFuture);

//        findPricesV1("Apple");

        findPrices("banana");

    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    // 同步方式计算价格集合，4s
    public static List<String> findPrices(String product) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop(" BuyItAll"));
        List<String> collect = shops.parallelStream().map(shop -> shop.getPrice(product)).map(Quote::parse).map(Discount::applyDiscount).collect(Collectors.toList());
//        List<String> collect = shops.stream().map(shop -> String.format("%s price is %s", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
        stopwatch.stop();
        System.out.println(stopwatch.toString());
        return collect;
    }

    // 同步方式计算价格集合，使用 parallelStream，1.1s
    public static List<String> findPricesV1(String product) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("MyFavoriteShop1"),
                new Shop("MyFavoriteShop2"),
                new Shop("MyFavoriteShop3"),
                new Shop("MyFavoriteShop4"),
                new Shop("MyFavoriteShop5"),
                new Shop("MyFavoriteShop6"),
                new Shop("MyFavoriteShop7"),
                new Shop(" BuyItAll"));
        List<String> collect = shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
        stopwatch.stop();
        System.out.println(stopwatch.toString());
        return collect;
    }

    // 使用 CompletableFuture 计算价格集合，1.076s
    public static List<CompletableFuture<String>> findPricesV2(String product) {
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("MyFavoriteShop1"),
                new Shop("MyFavoriteShop2"),
                new Shop("MyFavoriteShop3"),
                new Shop("MyFavoriteShop4"),
                new Shop("MyFavoriteShop5"),
                new Shop("MyFavoriteShop6"),
                new Shop("MyFavoriteShop7"),
                new Shop(" BuyItAll"));
        return shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), Executors.newFixedThreadPool(10)))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), Executors.newFixedThreadPool(10))))
                .collect(Collectors.toList());
//        return shops.stream().map(shop ->
//                CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), Executors.newFixedThreadPool(1))
//        ).collect(Collectors.toList());
    }


    // 异步计算价格方式，通过自定义线程并赋值给 CompletableFuture 实现。
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                future.complete(price);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();
        return future;
    }

    // 异步计算价格，通过 CompletableFuture 的工厂函数实现
    public Future<Double> getPriceAsyncV2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    // 同步计算价格方式
//    public double getPrice(String product) {
//        return calculatePrice(product);
//    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product
                .charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
