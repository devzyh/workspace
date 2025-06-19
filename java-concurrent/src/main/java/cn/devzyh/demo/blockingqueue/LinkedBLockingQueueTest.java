package cn.devzyh.demo.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 无界阻塞队列测试
 */
public class LinkedBLockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<Double> queue = new LinkedBlockingQueue<>();

        new Thread(() -> {
            while (true) {
                try {
                    double num = Math.random() * 10000;
                    queue.put(num);
                    System.out.println(Thread.currentThread().getName() + "发送了：" + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    double num = queue.take();
                    System.out.println(Thread.currentThread().getName() + "接收了：" + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);
        System.exit(0);
    }
}