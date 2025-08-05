package cn.devzyh.demo.blockingqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * 转发队列测试
 */
public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {

        // 队列容量为 0 不存储数据
        SynchronousQueue<Double> queue = new SynchronousQueue<>();

        new Thread(() -> {
            while (true) {
                double num = Math.random() * 10000;
                queue.offer(num);
                System.out.println(Thread.currentThread().getName() + "发送了：" + num);
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
