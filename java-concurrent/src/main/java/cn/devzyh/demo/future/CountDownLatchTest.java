package cn.devzyh.demo.future;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 案例：
 * 获取多个平台票价，最快的方式返回，允许丢弃某些超时的平台数据
 * 实现：
 * CountDownLatch（闭锁） 完成一个任务计数减一，减到0则所有任务执行完成
 *
 * @author devzyh
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        // 用于存储返回结果
        Set<String> prices = Collections.synchronizedSet(new HashSet<>());
        // 任务线程池
        ExecutorService service = Executors.newFixedThreadPool(3);
        // 用于任务计数
        CountDownLatch count = new CountDownLatch(3);

        // 多线程请求平台数据
        service.submit(new Task("BAT", prices, count));
        service.submit(new Task("TMD", prices, count));
        service.submit(new Task("OTH", prices, count));

        // 等待任务完成，8秒后超时
        try {
            count.await(8, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出计算数据
        prices.forEach(System.out::println);

        service.shutdown();
    }

    static class Task implements Runnable {

        private CountDownLatch count;
        private String id;
        private Set<String> prices;

        public Task(String id, Set<String> prices, CountDownLatch count) {
            this.id = id;
            this.prices = prices;
            this.count = count;
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
            // 获取成功，任务减一
            this.count.countDown();
        }
    }
}
