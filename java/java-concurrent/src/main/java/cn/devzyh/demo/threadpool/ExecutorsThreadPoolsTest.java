package cn.devzyh.demo.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors提供的线程池
 */
public class ExecutorsThreadPoolsTest {

    public static void main(String[] args) {
        /**
         * 固定线程数量的线程池
         * 核心线程数与最大线程数一致
         */
        Executors.newFixedThreadPool(5);

        /**
         * 动态创建销毁线程的线程池
         * 核心线程数与最大线程数都为Integer.MAX_VALUE
         * 任务队列不作存储只转发
         */
        Executors.newCachedThreadPool();

        /**
         * 单线程的线程池
         * 线程池中始终只有一个线程执行任务
         * 适合顺序执行的任务
         * 任务执行产生异常将会新建一个线程继续执行任务
         */
        Executors.newSingleThreadExecutor();

        /**
         * 固定核心线程数量的计划任务线程池
         *
         */
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        Runnable r = () -> {
            System.out.println("runner");
        };
        // 延迟指定时间后执行一次任务
        service.schedule(r, 1, TimeUnit.SECONDS);
        // 周期性执行任务，不管上次任务是否执行完毕
        service.scheduleAtFixedRate(r, 1, 1, TimeUnit.SECONDS);
        // 周期性执行任务，从上次任务执行完毕后开始计算时间
        service.scheduleWithFixedDelay(r, 1, 1, TimeUnit.SECONDS);

        /**
         * 一个核心线程的计划任务线程池
         * 使用方式同ScheduledThreadPool只是内部只采用一个工作线程
         */
        Executors.newSingleThreadScheduledExecutor();
    }
}
