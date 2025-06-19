package cn.devzyh.demo.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现的可重入自旋锁
 */
public class ReentrantSpinLock {

    private static AtomicReference<Thread> owner = new AtomicReference<>();
    private volatile static int lockCount; // 重入次数

    /**
     * 获取锁
     */
    public void lock() {
        Thread t = Thread.currentThread(); // 获取当前线程对象
        if (t == owner.get()) {
            // 如果当前线程是已获取锁的线程则重入
            lockCount++;
            return;
        } else {
            // 不是已经获取锁的线程则自选等待
            while (!owner.compareAndSet(null, t)) {
                System.out.println(t.getName() + "自旋了");
            }
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        Thread t = Thread.currentThread(); // 获取当前线程对象
        // 只有获取锁的线程才能释放锁
        if (t == owner.get()) {
            if (lockCount > 0) {
                // 减少重入次数
                lockCount--;
            } else {
                // 将当前获取锁的线程置为空
                owner.set(null);
            }
        }
    }

    public static void main(String[] args) {
        ReentrantSpinLock spinLock = new ReentrantSpinLock();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    spinLock.lock();
                    System.out.println(Thread.currentThread().getName() + "我获取到锁了");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName() + "我释放锁了");
                }
            }
        };

        new Thread(r).start();
        new Thread(r).start();
    }
}
