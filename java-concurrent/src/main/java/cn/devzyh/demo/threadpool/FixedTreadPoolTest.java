package cn.devzyh.demo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定数量线程池的使用
 */
public class FixedTreadPoolTest {

    public static void main(String[] args) {
        // 声明固定线程数量的线程池
        // 当前线程拥有5个核心线程
        // 线程池最多只能有5个线程
        // 空闲线程最多等待0秒
        // 一个链表形式的任务队列
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 1000; i++) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    // 线程的名称始终在thread-1到thread-5之间
                    System.out.println("thread name = " + Thread.currentThread().getName());
                }
            };
            System.out.println("向线程池提交了第" + i + "个线程");
            executor.submit(r);
        }
        executor.shutdown();
    }
}
