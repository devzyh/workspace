package cn.devzyh.demo.queue.ds;

/**
 * 双端队列的简单实现
 */
public class SimpleDeque<E> {

    int size; // 长度
    Node head; // 队头
    Node tail; // 队尾

    public SimpleDeque() {
        size = 0;
        head = new Node(null, null, tail);
        tail = new Node(null, head, null);
    }

    /**
     * 数据入队（队尾）
     *
     * @param e
     */
    void add(E e) {
        addLast(e);
    }


    /**
     * 数据入队（队头）
     *
     * @param e
     */
    void addFirst(E e) {
        Node n = new Node(e, head, head.next);
        head.next.prev = n;
        head.next = n;
        size++;
    }

    /**
     * 数据入队（队尾）
     *
     * @param e
     */
    void addLast(E e) {
        Node n = new Node(e, tail.prev, tail);
        tail.prev.next = n;
        tail.prev = n;
        size++;
    }

    /**
     * 数据出队（队头）
     *
     * @return
     */
    E poll() {
        return pollFirst();
    }

    /**
     * 数据出队（队头）
     *
     * @return
     */
    E pollFirst() {
        // 空队列了
        if (head.next == tail) {
            return null;
        }

        E e = head.next.val;
        head.next = head.next.next;
        head.next.prev = head;
        size--;
        return e;
    }

    /**
     * 数据出队（队尾）
     *
     * @return
     */
    E pollLast() {
        // 空队列
        if (tail.prev == head) {
            return null;
        }

        E e = tail.prev.val;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        size--;
        return e;
    }

    /**
     * 队列长度
     *
     * @return
     */
    int size() {
        return size;
    }

    /**
     * 数据节点
     */
    class Node {
        E val; // 数据域
        Node prev; // 前节点指针
        Node next; // 后节点指针

        public Node(E val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

}
