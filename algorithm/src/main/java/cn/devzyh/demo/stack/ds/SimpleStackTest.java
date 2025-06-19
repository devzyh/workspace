package cn.devzyh.demo.stack.ds;

public class SimpleStackTest {

    public static void main(String[] args) {
        SimpleStack<Integer> ss = new SimpleStack<>();
        ss.push(1);
        ss.push(2);
        ss.push(3);
        System.out.println(ss.peek());
        System.out.println(ss.pop());
        System.out.println(ss.pop());
        System.out.println(ss.pop());
        System.out.println(ss.pop());
    }
}
