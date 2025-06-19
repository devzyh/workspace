package cn.devzyh.demo.stack.ds;

/**
 * 栈 Stack
 * 基于链表简单实现的栈数据结构
 */

public class SimpleStack<E> {

    Node<E> top;

    /**
     * 压栈，向栈内添加数据
     *
     * @param e
     */
    void push(E e) {
        // 新进元素作为栈顶并链接到上一个元素
        Node n = new Node(e, top);
        top = n;
    }

    /**
     * 返回栈顶元素不删除
     *
     * @return
     */
    E peek() {
        if (top == null) {
            return null;
        }
        return top.val;
    }

    /**
     * 移除并返回栈顶元素
     *
     * @return
     */
    E pop() {
        E e = peek();
        if (top != null) {
            top = top.next;
        }
        return e;
    }

    /**
     * 判断内栈元素是否为空
     *
     * @return
     */
    boolean isEmpty() {
        return top == null;
    }
}

/**
 * 链表节点
 */
class Node<E> {
    E val;
    Node next;

    public E getVal() {
        return val;
    }

    Node() {
    }

    Node(E val) {
        this.val = val;
    }

    Node(E val, Node next) {
        this.val = val;
        this.next = next;
    }
}

