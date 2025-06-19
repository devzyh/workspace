package cn.devzyh.demo.blockingqueue;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 非阻塞并发队列
 */
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.offer("A"); // 队列使用cas函数进行操作，适合并发比较低的场景
        queue.offer("B"); // 队列不会阻塞，但是并发写比较高的时候会产生较多的自旋
        queue.offer("C");
        queue.offer("D");
        queue.offer("E");

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println("val = " + iterator.next());
        }

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            // System.out.println("first = " + queue.peek()); // 不会移除头结点
            System.out.println("first = " + queue.poll()); // 会移除头结点
        }
    }
}
