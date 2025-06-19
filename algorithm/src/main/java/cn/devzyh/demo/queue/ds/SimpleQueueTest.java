package cn.devzyh.demo.queue.ds;

public class SimpleQueueTest {

    public static void main(String[] args) {
        SimpleQueue<Integer> sq = new SimpleQueue<>();
        System.out.println(sq.poll());
        System.out.println(sq.poll());
        sq.add(1);
        sq.add(2);
        sq.add(3);
        sq.add(4);
        sq.add(5);
        System.out.println(sq.poll());
        System.out.println(sq.poll());
        System.out.println(sq.poll());
        System.out.println(sq.size());
    }
}
