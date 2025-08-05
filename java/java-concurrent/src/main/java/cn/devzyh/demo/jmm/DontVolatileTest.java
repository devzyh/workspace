package cn.devzyh.demo.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用volatile不能保证结果正确的场景测试
 */
public class DontVolatileTest implements Runnable {
    volatile int a;
    AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        DontVolatileTest task = new DontVolatileTest();

        // 多个线程对变量进行操作
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出结果
        System.out.println("a=" + task.a + ";atomicInteger=" + task.atomicInteger.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            a++; // 非原子操作
            atomicInteger.getAndIncrement(); // 原子类
        }
    }
}
