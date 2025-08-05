package cn.devzyh.demo.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * 长整形累加器测试
 */
public class LongAdderTest {

    public static void main(String[] args) throws InterruptedException {

        // 累加器可以分段计算最后通过 sum 获取最终值
        LongAdder adder = new LongAdder();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    adder.add(1);
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(r);
            t.start();
        }

        Thread.sleep(5000);
        System.out.println(adder.sum());
    }
}
