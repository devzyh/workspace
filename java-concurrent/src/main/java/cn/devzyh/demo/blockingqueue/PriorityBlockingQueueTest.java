package cn.devzyh.demo.blockingqueue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 带优先级的无界阻塞队列测试
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        // 比较器对象
        Comparator<Integer> c = (Integer o1, Integer o2) -> o2 - o1;
        // 指定初始队列容量以及元素比较器
        // 内部数据结构使用数组
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(5, c);
        queue.put(2);
        queue.put(3);
        queue.put(9);
        queue.put(5);
        queue.put(7);

        while (queue.size() > 0) {
            // 将会安装优先级的升序进行出队列
            System.out.println(queue.take());
        }
    }
}
