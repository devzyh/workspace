package cn.devzyh.demo.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式实现
 * 基于可重入锁实现阻塞队列
 */
public class ProducerConsumerTest {

    /**
     * 内部实现的阻塞队列
     */
    static class BlockingQueue<T> {
        private Queue<T> queue; // 存放数据的队列
        private int max = 8; // 队列最大元素总数
        private ReentrantLock lock = new ReentrantLock(); // 可重入锁
        private Condition notEmpty = lock.newCondition(); // 队列非空条件
        private Condition notFull = lock.newCondition(); // 队列非满条件

        public BlockingQueue(int size) {
            this.max = size;
            this.queue = new LinkedList<T>();
        }

        // 存放数据
        public void put(T el) {
            lock.lock(); // 获取锁
            try {
                while (this.queue.size() == this.max) {
                    // 队列满时，非满条件阻塞，不能再生产数据
                    notFull.await();
                }
                queue.add(el);
                notEmpty.signalAll(); // 通知非空条件线程消费数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // 释放锁
            }
        }

        // 取出数据
        public T take() {
            lock.lock(); // 获取锁
            T el = null;
            try {
                while (this.queue.size() == 0) { // 采用isEmpty()方法判断更好
                    // 队列空时，非空条件线程阻塞，不能再消费数据
                    notEmpty.await();
                }
                el = queue.remove();
                notFull.signalAll(); // 通知非满条件线程生产数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // 释放锁
            }
            return el;
        }
    }

    /**
     * 生产者线程
     */
    static class Producer implements Runnable {
        private BlockingQueue blockingQueue;

        public Producer(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                this.blockingQueue.put(i);
                System.out.println("Producer = " + i);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者线程
     */
    static class Consumer implements Runnable {
        private BlockingQueue blockingQueue;

        public Consumer(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Object data = this.blockingQueue.take();
                System.out.println("Consumer = " + data);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new BlockingQueue<Integer>(16);

        new Thread(new Producer(queue)).start(); // 启动生产者
        Thread consumer = new Thread(new Consumer(queue));
        consumer.start(); // 启动消费者
        consumer.join(); // 等待消费者执行完毕
    }

}
