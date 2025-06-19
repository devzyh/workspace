package cn.devzyh.demo.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock是锁的接口
 * ReentrantLock是Lock接口的主要实现类
 */
public class LockMethodTest {

    /**
     * 基本用法
     */
    static void simpleTest() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock(); // 未获取到锁时线程将进入等待状态
        System.out.println("锁已获取成功");
        try {
            System.out.println("这是业务逻辑");
            throw new Exception("业务逻辑产生的异常");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 记得释放锁不然可能产生死锁
            if (lock != null && lock.isHeldByCurrentThread() && lock.isLocked()) {
                lock.unlock();
                System.out.println("锁已被释放");
            }
        }
    }

    /**
     * tryLock()方法
     * 当前方法会尝试获取锁获取失败则不会等待
     * tryLock带有时间参数的重载方法将会等待指定的时间：
     * 1.等待期间获取到锁则返回true
     * 2.等待期间也可以响应中断信号
     * 可以使用这个方法来避免死锁的产生
     */
    static void tryLockTest() {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        // 线程1
        new Thread(() -> {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        if (lock2.tryLock()) {
                            try {
                                System.out.println("线程1的业务逻辑");
                                try {
                                    Thread.sleep(new Random().nextInt(1000));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } finally {
                                lock2.unlock();
                            }
                        } else {
                             System.out.println("线程1获取lock2失败");
                        }
                    } finally {
                        lock1.unlock();
                    }
                } else {
                    System.out.println("线程1获取lock1失败");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        // 线程2
        new Thread(() -> {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        if (lock1.tryLock()) {
                            try {
                                System.out.println("线程2的业务逻辑");
                                try {
                                    Thread.sleep(new Random().nextInt(1000));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } finally {
                                lock1.unlock();
                            }
                        } else {
                             System.out.println("线程2获取lock1失败");
                        }
                    } finally {
                        lock2.unlock();
                    }
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                     System.out.println("线程2获取lock2失败");
                }
            }
        }).start();
    }

    /**
     * lockInterruptibly
     * 可响应中断信号的上锁方法
     */
    static void lockInterruptiblyTest() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("执行业务逻辑");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread() && lock.isLocked()) {
                    lock.unlock();
                }
            }
        }).start();

        Thread th = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("获取锁成功");
            } catch (InterruptedException e) {
                System.out.println("响应到中断信号");
            } finally {
                if (lock.isHeldByCurrentThread() && lock.isLocked()) {
                    lock.unlock();
                }
            }
        });

        th.start();
        Thread.sleep(3000);
        th.interrupt();
    }

    /**
     * newCondition
     * 用于创建不同条件下的线程协作对象
     * 调用Condition的await()和signal()方法时需要先持有相关的锁
     */
    static void newConditionCTest() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread th = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("下面线程将进入等待");
                condition.await();
            } catch (InterruptedException e) {
                System.out.println("响应到中断信号");
            } finally {
                lock.unlock();
            }
        });
        th.start();
        Thread.sleep(1000);
        th.interrupt();
        System.out.println("程序执行完毕");
    }

    /**
     * @param args
     */

    public static void main(String[] args) throws InterruptedException {
//        simpleTest();
//        tryLockTest();
//        lockInterruptiblyTest();
        newConditionCTest();
    }
}
