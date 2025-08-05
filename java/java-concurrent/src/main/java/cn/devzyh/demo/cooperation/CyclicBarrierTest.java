package cn.devzyh.demo.cooperation;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 循环屏障工具类测试
 * 用途：线程等待满足一组的数量后统一开始执行，可以在执行前定义一个方法
 * 特点：屏障可以循环使用，可以在屏障放开时执行一个Runnable
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        // 满足3个线程到等待时，放开屏障，放开前先执行传入的Runnable对象
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("=========================================");
            System.out.println("满足3个任务数量，放行这一波任务");
        });

        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 提交12个任务执行
        IntStream.range(0, 12).forEach((i) -> {
            pool.submit(() -> {
                System.out.println("任务" + i + "开始进行准备工作");
                try {
                    Thread.sleep((long) (1000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务" + i + "准备完成，开始等待其他任务准备完成");

                try {
                    // 等待这组任务全部准备完成
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis() + "任务" + i + "开始执行了");
            });
        });

        pool.shutdown();
    }
}
