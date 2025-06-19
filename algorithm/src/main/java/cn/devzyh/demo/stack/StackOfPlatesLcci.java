package cn.devzyh.demo.stack;

/**
 * https://leetcode.cn/problems/stack-of-plates-lcci/
 */
public class StackOfPlatesLcci {

    public static void main(String[] args) {
        // [null, null, null, 2, 1, -1]
        StackOfPlates sop = new StackOfPlates(1);
        sop.push(1);
        sop.push(2);
        System.out.println(sop.popAt(1));
        System.out.println(sop.pop());
        System.out.println(sop.pop());

        System.out.println();

        // [null, null, null, null, 2, 1, 3]
        StackOfPlates sop1 = new StackOfPlates(2);
        sop1.push(1);
        sop1.push(2);
        sop1.push(3);
        System.out.println(sop1.popAt(0));
        System.out.println(sop1.popAt(0));
        System.out.println(sop1.popAt(0));
        System.out.println(sop1.popAt(0));

        System.out.println();

        // [null,null,null,null,-1,1,3]
        StackOfPlates sop2 = new StackOfPlates(2);
        sop2.push(3);
        sop2.push(3);
        sop2.push(1);
        System.out.println(sop2.popAt(5));
        System.out.println(sop2.pop());
        System.out.println(sop2.pop());
    }
}

class StackOfPlates {

    private int stackMaxCap = 1; // 单栈最大容量
    private Node firstStack = null; // 首栈引用
    private Node lastStack = null; // 尾栈引用
    private int lastStackSize = 0; // 尾栈大小
    private int stackCount = 0; // 栈数量

    public StackOfPlates(int cap) {
        stackMaxCap = cap;
    }

    /**
     * 数据入栈 O(1)
     *
     * @param val
     */
    public void push(int val) {
        if (firstStack == null) {
            // 首栈为空创建一个首栈
            firstStack = lastStack = new Node();
            stackCount++;
        } else {
            // 尾栈容量满新加一个栈
            if (lastStackSize >= stackMaxCap) {
                Node last = new Node();
                lastStack.next = last;
                lastStack = last;
                lastStackSize = 0;
                stackCount++;
            }
        }

        // 尾栈内压入数据
        if (lastStack.val == null) {
            // 空栈压入
            lastStack.val = new Node(val);
        } else {
            // 非空压入
            Node addNode = new Node(val, (Node) lastStack.val);
            lastStack.val = addNode;
        }

        // 尾栈大小增加
        lastStackSize++;
    }

    /**
     * 数据出栈，尾栈出
     */
    public int pop() {
        return popAt(stackCount - 1);
    }

    /**
     * 指定栈数据出栈 O(n)
     *
     * @param index
     */
    public int popAt(int index) {
        if (firstStack == null) {
            return -1;
        }
        if (firstStack.val == null) {
            return -1;
        }

        // 查找指定栈 O(n)
        Node indexStack = null;
        Node prevStack = null;
        Node curStack = firstStack;
        int i = 0;
        while (indexStack == null && curStack != null) {
            if (i == index) {
                indexStack = curStack;
                break;
            }
            prevStack = curStack;
            curStack = curStack.next;
            i++;
        }
        // 没找到
        if (indexStack == null) {
            return -1;
        }
        // 找到空栈
        if (indexStack.val == null) {
            return -1;
        }

        // 数据出栈 O(1)
        Node head = (Node) indexStack.val;
        int val = (int) head.val;
        indexStack.val = head.next;

        // 空栈移除 非满不处理
        if (indexStack.val == null) {
            if (prevStack == null) {
                // 当前是首栈
                firstStack = indexStack.next;
            } else {
                // 移除当前栈，连接前后栈
                prevStack.next = indexStack.next;
                stackCount--;
            }
        }

        return val;
    }

    /**
     * 链表节点
     */
    class Node {
        Object val; // 数据域
        Node next; // 指针域

        public Node() {
        }

        public Node(Object val) {
            this.val = val;
        }

        public Node(Object val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
