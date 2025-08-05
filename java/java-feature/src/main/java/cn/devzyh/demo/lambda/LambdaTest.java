package cn.devzyh.demo.lambda;

import java.util.function.Function;

public class LambdaTest {

    public static void main(String[] args) {
        // 第一个函数式传加法
        Function<Integer, Integer> f1 = (Integer num) -> (num + num);
        showResult(f1);

        // 第二个函数式传乘法
        Function<Integer, Integer> f2 = (Integer num) -> (num * num);
        showResult(f2);
    }

    /**
     * 传入不同的处理函数，返回不同的结果
     *
     * @param f
     */
    static void showResult(Function<Integer, Integer> f) {
        System.out.println("我调用了一个方法，传入不同的方法返回结果不同");
        System.out.println(f.apply(100));
    }

}
