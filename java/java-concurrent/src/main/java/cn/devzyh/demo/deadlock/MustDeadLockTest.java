package cn.devzyh.demo.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 必然死锁的例子
 * 线程互相等待对方释放自己拥有的锁
 * <p>
 * 死锁发生条件：
 * 1.互斥条件：对同一资源的访问有限制
 * 2.请求与保持条件：保持当前资源不释放，并去请求新的资源
 * 3.不剥夺条件：不会中断请求
 * 4.循环等待条件：多个线程之间互相等待资源释放
 */
public class MustDeadLockTest {

    public static void main(String[] argv) throws InterruptedException {
        // 锁对象
        Integer num1 = new Integer("1");
        Integer num2 = new Integer("2");

        // 线程1
        Thread t1 = new Thread(() -> {
            // 先获取num1对象锁
            synchronized (num1) {
                System.out.println("线程1获取到num1对象锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 休眠1秒后去获取num2对象锁
                synchronized (num2) {
                    System.out.println("线程1获取到了两把锁");
                }
            }
        });

        // 线程2
        Thread t2 = new Thread(() -> {
            // 先获取num2对象锁
            synchronized (num2) {
                System.out.println("线程2获取到num2对象锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 休眠1秒后再去获取num1对象锁
                synchronized (num1) {
                    System.out.println("线程2获取到两把锁");
                }
            }
        });

        // 启动线程
        t1.start();
        t2.start();

        // 由于死锁情况发生，导致都获取不到对方手中的锁，也就无法打印出第二句话

        Thread.sleep(3000);

        /**
         * 使用代码监控死锁
         */
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreads != null && deadlockedThreads.length > 0) {
            for (int i = 0; i < deadlockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThreads[i]);
                System.out.printf("线程ID[%s]线程名[%s]发生死锁，获取锁的持有者为[%s]\n",
                        threadInfo.getThreadId(), threadInfo.getThreadName(), threadInfo.getLockOwnerName());
            }
        }

        /**
         * 工具查看死锁
         * jps 查看java进程pid
         * jstack pid 查看java进程详细信息
         */

    }
}
