package javaasyncinaction;

import com.sun.org.apache.bcel.internal.generic.LNEG;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import util.SleepUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TestCompletableFutureSet {

    // 无返回值的 CompletableFuture
    @Test
    public void testNoReturn() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            SleepUtil.sleepSeconds(3);
        });
        Assert.assertNull(future.get());
    }

    // 有返回值的 CompletableFuture
    @Test
    public void testHasReturn() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            SleepUtil.sleepSeconds(3);
            return "hello world!";
        });
        Assert.assertNull(future.getNow(null));
        Assert.assertEquals("hello world!", future.get());
    }

    // 调用 thenRun 不会使用到上一个 CompletableFuture 的返回值，第二个 CompletableFuture 也没有返回值。
    @Test
    public void testThenRun() throws Exception {
        // 执行线程是 ForkJoin pool 线程
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "hello world";
        });
        // 如果调用的是 thenRun 则执行线程是 main 线程，如果调用的是 thenRunAsync，则是由 ForkJoin pool 线程执行
        CompletableFuture<Void> future = future1.thenRunAsync(() -> System.out.println(Thread.currentThread().getName()));
        Assert.assertNull(future.get());
    }

    // 调用 thenAccept 会接收到上一个 CompletableFuture 的返回值。第二个 CompletableFuture 没有返回值。
    @Test
    public void testThenAccept() throws Exception {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            SleepUtil.sleepOneSecond();
            return "hello ";
        });
        CompletableFuture<Void> future = f1.thenAcceptAsync(s -> System.out.println(s + "world"));
        Assert.assertNull(future.get());
    }

    // 调用 thenApply 会接收到上一个 CompletableFuture 的返回值。第二个 CompletableFuture 也有返回值。
    @Test
    public void testThenApply() throws Exception {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            SleepUtil.sleepOneSecond();
            return "hello ";
        });
        CompletableFuture<String> future = f1.thenApply(s -> s + "world");
        Assert.assertEquals("hello world", future.get());
    }


    // 当任务执行结束时，调用 whenComplete 方法，参数是调用序列的最终返回结果以及抛出的异常。
    // 如果有异常，那么认为该调用序列出现了问题。
    @Test
    public void testWhenComplete() throws Exception {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            SleepUtil.sleepOneSecond();
            return "hello world!";
        });
        CompletableFuture<String> f2 = f1.whenComplete((r, t) -> {
            if (t == null) {
                System.out.println(r);
            } else {
                System.out.println(t.getMessage());
            }
        });
        Assert.assertEquals("hello world!", f2.get());
    }

    public static void main(String[] args) throws Exception {
        // 测试 thenCompose，代表
//        CompletableFuture<String> f1 = doSomethingOne("abc");
//        CompletableFuture<String> result = f1.thenCompose(TestCompletableFutureSet::doSomethingTwo);
//        System.out.println(result.get());

        // thenCombine 并发执行
        CompletableFuture<String> result = doSomethingOne("abc").thenCombine(doSomethingTwo("cba"), (one, two) -> one + " " + two);
        System.out.println(result.get());

        // allOf 是所有的都并发执行
        List<CompletableFuture<String>> list = new ArrayList<>();
        list.add(doSomethingOne("abc"));
        list.add(doSomethingOne("abc"));
        list.add(doSomethingOne("abc"));
        list.add(doSomethingOne("abc"));
        list.add(doSomethingOne("abc"));
        list.add(doSomethingOne("abc"));
        list.add(doSomethingOne("abc"));
        list.add(doSomethingOne("abc"));
        CompletableFuture<Void> future = CompletableFuture.allOf(list.toArray(new CompletableFuture[0]));
        System.out.println(future.get());

    }

    private static CompletableFuture<String> doSomethingOne(String encryptionId) {
        return CompletableFuture.supplyAsync(() -> {
            SleepUtil.sleepSeconds(3);
            return encryptionId;
        });
    }

    private static CompletableFuture<String> doSomethingTwo(String id) {
        return CompletableFuture.supplyAsync(() -> {
            SleepUtil.sleepSeconds(3);
            return StringUtils.reverse(id);
        });
    }

}
