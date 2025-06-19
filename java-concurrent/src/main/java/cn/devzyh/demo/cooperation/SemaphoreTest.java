package cn.devzyh.demo.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量测试
 * 使用信号量控制线程最大访问数量
 *
 * @author devzyh
 */
public class SemaphoreTest {

    // 构造信号量对象并指定证书数量
    static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            service.submit(new Task());
        }

        // 等待任务执完毕
        service.shutdown();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                // 获取证书，获取成功，证书信号量证书数量减一，没有证书可获取时阻塞
                // 可以传入数量来获取指定数量的证书
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 执行任务
            try {
                Thread.sleep(2000);
                System.out.println((System.currentTimeMillis() / 1000) + ":" + Thread.currentThread().getName() + ":执行任务耗时2秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 释放证书，可以指定释放的数量
            semaphore.release();
        }
    }
}
