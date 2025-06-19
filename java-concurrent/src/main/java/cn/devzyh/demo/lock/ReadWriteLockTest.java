package cn.devzyh.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁使用测试
 * 读写锁的升降级
 */
public class ReadWriteLockTest {

    // 缓存读取设置
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock(); // 共享读锁
    private static Lock writeLock = readWriteLock.writeLock(); // 排它写锁

    private static volatile boolean cacheValid; // 缓存是否有效
    private static volatile Object data; // 缓存数据

    public static void main(String[] args) {
        // 读锁上锁
        readLock.lock();
        try {

            // 缓存无效准备写缓存
            if (cacheValid == false) {

                readLock.unlock(); // 先释放读锁才能被写锁获取到
                writeLock.lock();
                try {

                    if (data == null) {
                        data = new Object();
                        cacheValid = true;
                    }
                    // 关键数据改变完毕后，可以对读写锁进行降级为读锁方便读线程能及时读取
                    // 因为是在同一个线程所以可以不释放写锁的情况下，再次上读锁
                    readLock.lock();
                } finally {
                    writeLock.unlock();
                }
            }

            // 输出数据
            System.out.println("cache data = " + data);
        } finally {
            readLock.unlock();
        }
    }
}
