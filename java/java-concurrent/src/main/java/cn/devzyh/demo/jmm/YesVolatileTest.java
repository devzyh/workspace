package cn.devzyh.demo.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用volatile能保证结果正确的场景测试
 * <p>
 * 还可以通过volatile修饰的变量来控制程序初始化；注意变量位置，利用的是变量写happen-before读的特性。
 */
public class YesVolatileTest implements Runnable {
    volatile boolean a;
    AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        YesVolatileTest task = new YesVolatileTest();

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
            a = true; // 变量赋值是原子操作，写happen-before读
            atomicInteger.getAndIncrement(); // 原子类
        }
    }
}
