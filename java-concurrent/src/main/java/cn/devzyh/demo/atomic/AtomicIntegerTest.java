package cn.devzyh.demo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 整数型原子类测试
 */
public class AtomicIntegerTest {

    public static void main(String[] args) {

        AtomicInteger ai = new AtomicInteger();

        // 设置值 内部使用 CAS 锁
        ai.set(123);

        // 获取值并自增 1
        System.out.println(ai.getAndIncrement()); // 123

        // 获取值
        System.out.println(ai.get()); // 124

        // 获取值并自减 1
        System.out.println(ai.getAndDecrement()); // 124

        System.out.println(ai.get()); // 123

        // 加上指定值
        ai.getAndAdd(20);
        System.out.println(ai.get()); // 143

        // 和期望值 143 进行比较 如果相同则更新
        ai.compareAndSet(143, 150);
        System.out.println(ai.get()); // 150
    }
}
