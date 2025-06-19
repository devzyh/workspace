package cn.devzyh.demo.queue;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class ImplementQueueUsingStacks {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.peek()); // return 1
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.empty()); // return false

    }
}

class MyQueue {

    Stack<Integer> inputStack;
    Stack<Integer> outputStack;

    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    /**
     * 向队列添加数据 O(1)
     *
     * @param x
     */
    public void push(int x) {
        inputStack.push(x);
    }

    /**
     * 移出队头数据 O(n)
     *
     * @return
     */
    public int pop() {
        // 存储到输出栈并翻转顺序
        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.pop());
        }
        // 移出输出栈顶数据
        int e = outputStack.pop();
        // 存储到输入栈并翻转顺序
        while (!outputStack.isEmpty()) {
            inputStack.push(outputStack.pop());
        }
        return e;
    }

    /**
     * 获取队头数据 O(n)
     *
     * @return
     */
    public int peek() {
        // 存储到输出栈并翻转顺序
        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.pop());
        }
        // 获取输出栈顶数据
        int e = outputStack.peek();
        // 存储到输入栈并翻转顺序
        while (!outputStack.isEmpty()) {
            inputStack.push(outputStack.pop());
        }
        return e;
    }

    /**
     * 判断队列是否为空 O(1)
     *
     * @return
     */
    public boolean empty() {
        return inputStack.isEmpty();
    }
}
