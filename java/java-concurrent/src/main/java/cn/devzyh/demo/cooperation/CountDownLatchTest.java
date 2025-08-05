package cn.devzyh.demo.cooperation;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并发工具闭锁的测试
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        // countForEnd();
        countForStart();
    }

    /**
     * 用闭锁判断线程全部结束
     */
    static void countForEnd() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        // 指定数量为5的闭锁
        CountDownLatch count = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            final int finalI = i;

            service.submit(() -> {
                // 随机时间延迟，完成后闭锁减一
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("任务" + finalI + "执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 闭锁减一
                    count.countDown();
                }
            });
        }

        try {
            // 等待所有任务执行完毕
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有任务执行完成");
    }

    /**
     * 用闭锁控制线程全部开始
     */
    static void countForStart() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        // 指定数量为1的闭锁
        CountDownLatch count = new CountDownLatch(1);

        for (int i = 0; i < 5; i++) {
            final int finalI = i;

            service.submit(() -> {
                // 随机时间延迟，完成后进入闭锁等待
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("任务" + finalI + "准备完成，进入等待...");
                    count.await();
                    System.out.println("任务" + finalI + "开始执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 1秒后闭锁减一，任务开始执行
        count.countDown();
        System.out.println("任务不等待，立即开始执行");
    }
}
