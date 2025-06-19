package cn.devzyh.demo.queue.ds;

public class SimpleDequeTest {

    public static void main(String[] args) {
        SimpleDeque<String> deque = new SimpleDeque<>();
        deque.add("a");
        deque.add("b");
        deque.add("c");
        deque.add("d");
        deque.addFirst("1");
        deque.addFirst("2");
        System.out.println(deque.poll());
        System.out.println(deque.poll());
        System.out.println(deque.poll());
        System.out.println(deque.poll());
        System.out.println(deque.pollLast());
    }
}
