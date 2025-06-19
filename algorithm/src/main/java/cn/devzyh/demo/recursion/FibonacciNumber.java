package cn.devzyh.demo.recursion;

/**
 * https://leetcode.cn/problems/fibonacci-number/
 */
public class FibonacciNumber {

    public static void main(String[] args) {
        System.out.println(fib(1));
        int n = 2; // 1 F(2) = F(1) + F(0) = 1 + 0 = 1
        System.out.println(fib(n));

        n = 3; // 2 F(3) = F(2) + F(1) = 1 + 1 = 2
        System.out.println(fib(n));

        n = 4; // 3 F(4) = F(3) + F(2) = 2 + 1 = 3
        System.out.println(fib(n));
    }

    /**
     * 递归实现
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        // 退出条件
        if (n == 1) {
            return 1; // F(1) = 1
        }
        if (n < 1) {
            return 0; // F(0) = 0
        }

        // 函数功能与等价式
        return fib(n - 1) + fib(n - 2);
    }


}
