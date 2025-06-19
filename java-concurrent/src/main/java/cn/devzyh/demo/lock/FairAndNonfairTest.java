package cn.devzyh.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与非公平锁测试
 */
public class FairAndNonfairTest {

    public static void main(String[] args) {

        // 参数设置是否为公平锁
        // true：公平锁，每个线程获取到锁的概率相同
        // false：非公平锁，减少上下文切换，效率优先，可能产生饥饿线程
        Lock lock = new ReentrantLock(true);

        // 创建线程
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Job(lock), "Thread-" + i);
        }

        //启动线程
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 任务线程实现类
    static class Job implements Runnable {
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.printf("%s开始执行任务\n", Thread.currentThread().getName());

            lock.lock();
            try {
                long duration = (long) (Math.random() * 1000);
                System.out.printf("%s正在执行任务①,预计花费时间%d\n", Thread.currentThread().getName(), duration);
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            lock.lock();
            try {
                long duration = (long) (Math.random() * 1000);
                System.out.printf("%s正在执行任务②,预计花费时间%d\n", Thread.currentThread().getName(), duration);
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            System.out.printf("%s任务执行完毕\n", Thread.currentThread().getName());
        }
    }

}
