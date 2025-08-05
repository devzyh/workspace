package cn.devzyh.demo.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 关闭线程池的方法
 */
public class ShutdownThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            pool.submit(() -> {
                System.out.println("我还在执行啊");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 开始执行关闭线程池，直到工作线程和任务队列里的任务全部执行完毕才会彻底关闭
        pool.shutdown();
        // 向工作线程发送中断信号，将任务队列的任务以List形式返回
        List<Runnable> noRunList = pool.shutdownNow();
        System.out.println("count = " + noRunList.size());
        // 判断线程是否开始关闭
        System.out.println("isShutdown = " + pool.isShutdown());
        // 判断线程池是否已结束
        System.out.println("isTerminated = " + pool.isTerminated());

        // 等待指定时间来判断线程池是否关闭
        // 等待时间内关闭会直接返回true
        // 超过等待时间会返回false
        // 等待时间内响应到中断信号会抛出异常并返回false
        try {
            System.out.println("" + pool.awaitTermination(1000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭线程池中的某个任务可以通过Future类的cancel()方法实现
    }
}
