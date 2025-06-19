package cn.devzyh.demo.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有界阻塞队列常用操作测试
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) throws Exception {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);

        try {
            queue.add(4); // 队列已满会触发异常
        } catch (Exception e) {
            System.out.println("入队异常");
        }

        System.out.println(queue.offer(5)); // 队列已满会返回 false

        // queue.put(6); // 队列已满会阻塞入队线程

        System.out.println(queue.element()); // 返回队列头元素，如果为空抛出异常
        System.out.println(queue.peek()); // 返回队列头元素，如果为空返回 null
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

        try {
            System.out.println(queue.remove()); // 队列已空会触发异常
        } catch (Exception e) {
            System.out.println("入出队异常");
        }

        System.out.println(queue.poll());  // 队列已空会返回 null
        System.out.println(queue.poll(1, TimeUnit.SECONDS)); // 队列已空,1s 后还是空才会返回 null

        System.out.println(queue.take()); // 队列已空会阻塞出队线程
        System.out.println("我被执行了");
    }
}
