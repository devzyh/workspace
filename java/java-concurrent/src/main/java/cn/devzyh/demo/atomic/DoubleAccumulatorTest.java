package cn.devzyh.demo.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.IntStream;

/**
 * 浮点型积累器测试
 * 比累加器更通用的计算原子类
 */
public class DoubleAccumulatorTest {

    public static void main(String[] args) throws InterruptedException {

        // 声明累加器
        DoubleAccumulator accumulator = new DoubleAccumulator(new DoubleBinaryOperator() {
            @Override
            public double applyAsDouble(double left, double right) {
                // left 上一次计算的结果
                // right 本次传入的值
                System.out.printf("accumulator: %s + %s = %s \n", left, right, left + right);
                return left + right; // 具体的计算方式
            }
        }, // 计算方式
                0 // 初始值
        );

        // 并行计算结果
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        IntStream.range(1, 10).forEach(i -> {
            threadPool.submit(() -> accumulator.accumulate(i));
        });

        // 串行计算结果
        int num = 0;
        for (int i = 1; i < 10; i++) {
            num += i;
        }
        System.out.println("singleThread: num = " + num);

        // 延迟一段时间后获取计算结果并重置
        Thread.sleep(5000);
        System.out.println("accumulator = " + accumulator.getThenReset());
    }
}
