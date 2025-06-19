package cn.devzyh.demo.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁公平与公平测试
 */
public class ReadWriteLockFairAndNonfairTest {

    public static void main(String[] args) {

//        fairTest();

        nonfairTest();
    }

    /**
     * 公平锁新线程加入时会使用hasQueuedPredecessors()方法判断任务队列中是否还有线程，如果有则排队
     */
    static void fairTest() {

        // 公平锁 参数默认为：false
        ReentrantReadWriteLock fairLock = new ReentrantReadWriteLock(true);

    }

    /**
     * 非公平锁
     * <p>
     * 新线程加入时：如果任务队列头结点为写锁线程
     * 则不允许插队，读锁线程必须等待其他线程释放后才能获取锁
     * 否则允许插队，读锁线程可以多个共享，提高效率。
     * <p>
     * 如果允许读锁线程插队，则会造成写锁线程产生”饥饿“
     */
    static void nonfairTest() {
        ReentrantReadWriteLock nonfairLock = new ReentrantReadWriteLock(false);
        ReentrantReadWriteLock.ReadLock readLock = nonfairLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = nonfairLock.writeLock();

        // 读锁操作线程
        Runnable rRead = () -> {
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " read start");
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + " read end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        };

        // 写锁操作线程
        Runnable rWrite = () -> {
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " write start");
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + " write end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        };

        // 非公平锁测试，若启动顺序不能保证可以加延时
        new Thread(rRead, "thread-1").start();
        new Thread(rRead, "thread-2").start();
        new Thread(rWrite, "thread-3").start(); // 读写锁互斥，必须等待读锁全部释放后才能获取写锁
        new Thread(rRead, "thread-4").start(); // 必须等待写锁释放才能获取读锁
    }
}
