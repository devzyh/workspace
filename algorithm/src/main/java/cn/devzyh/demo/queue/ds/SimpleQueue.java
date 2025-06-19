package cn.devzyh.demo.queue.ds;

/**
 * 基于链表实现的简单队列
 */
public class SimpleQueue<E> {

    private Node head; // 队头
    private Node tail; // 队尾
    private int size; // 队列长度

    public SimpleQueue() {
        head = tail = new Node<E>();
        size = 0;
    }

    /**
     * 向队尾添加数据
     *
     * @param e
     */
    void add(E e) {
        Node n = new Node<E>(e);
        tail.next = n;
        tail = n;
        size++;
    }

    /**
     * 移除对头节点并返回
     *
     * @return
     */
    E poll() {
        // 队列空直接返回
        if (head.next == null) {
            return null;
        }

        Node<E> n = head.next;
        head = n;
        size--;
        return n.val;
    }

    /**
     * 返回队列长度
     *
     * @return
     */
    int size() {
        return size;
    }
}

/**
 * 链表节点
 *
 * @param <E>
 */
class Node<E> {
    E val;
    Node next;

    public Node() {
    }

    public Node(E val) {
        this.val = val;
    }

    public Node(E val, Node next) {
        this.val = val;
        this.next = next;
    }

    public E getVal() {
        return val;
    }
}
