package cn.devzyh.demo.cooperation;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁条件的使用
 * 通过条件阻塞或唤醒线程
 */
public class ConditionTest {

    public static void main(String[] args) {
        // 创建一个锁对象
        Lock lock = new ReentrantLock();

        // 创建一个锁条件
        Condition condition = lock.newCondition();

        // 线程1
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("线程1获取到锁");
                System.out.println("线程1开始等待");
                condition.await(); // 条件进入阻塞并释放锁
                System.out.println("线程1继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程1释放锁");
            }
        }).start();

        // 线程2
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("线程2获取到锁");
                System.out.println("线程2等待3秒后发送唤醒信号");
                Thread.sleep(3000);
                condition.signal(); // 唤醒条件一个线程
                // condition.signalAll(); // 唤醒条件下所有线程
                System.out.println("线程2唤醒信号已发送");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程2释放锁");
            }
        }).start();

    }
}
