package cn.devzyh.demo.future;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 案例：
 * 获取多个平台票价，最快的方式返回，允许丢弃某些超时的平台数据
 * 实现：
 * CompleteFuture 异步执行任务，可以统一控制结果获取
 *
 * @author devzyh
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        // 用于存储返回结果
        Set<String> prices = Collections.synchronizedSet(new HashSet<>());

        // 异步执行任务
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(new Task("BAT", prices));
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(new Task("TMD", prices));
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(new Task("OTH", prices));

        // 统一管理任务结果
        CompletableFuture<Void> allOf = CompletableFuture.allOf(task1, task2, task3);

        // 等待任务完成，6秒后超时
        try {
            allOf.get(6, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 输出计算数据
        prices.forEach(System.out::println);
    }

    static class Task implements Runnable {
        private String id;
        private Set<String> prices;

        public Task(String id, Set<String> prices) {
            this.id = id;
            this.prices = prices;
        }

        @Override
        public void run() {
            // 随机延迟模拟网络请求延迟
            long time = (long) (10000 * Math.random());
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.id + "平台耗时：" + time + "毫秒");
            long price = (long) (Math.random() * 1000);
            this.prices.add(this.id + ":" + price);
        }
    }
}
