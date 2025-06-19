package cn.devzyh.demo.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 长整型形字段升级原子类测试
 */
public class AtomicLongFieldUpdateTest {

    public static void main(String[] args) throws InterruptedException {
        Simple s1 = new Simple();
        Simple s2 = new Simple();
        AtomicIntegerFieldUpdater<Simple> numUpdater = AtomicIntegerFieldUpdater.newUpdater(Simple.class, "num");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    s1.num++; // 普通变量自增操作
                    numUpdater.getAndIncrement(s2); // 原子类包装变量操作
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("普通变量自增完成后：" + s1.num);
        System.out.println("原子类包装变量自增完成后：" + s2.num);

        // 引用类型原子包装类
        AtomicReferenceFieldUpdater<Simple, String> nameUpdater =
                AtomicReferenceFieldUpdater.newUpdater(Simple.class, String.class, "name");
        Simple simple = new Simple();
        simple.name = "张三";
        System.out.println(simple.name);
        nameUpdater.set(simple, "李四");
        System.out.println(simple.name);
        System.out.println(nameUpdater.get(simple));
    }

    // 成绩类
    static class Simple {
        public volatile int num; // 本地内存可见的变量
        public volatile String name;
    }
}
