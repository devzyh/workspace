package cn.devzyh.demo.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 整型数组原子类测试
 */
public class AtomicIntegerArrayTest {

    public static void main(String[] args) {

        // 创建一个长度为5的数组原子类
        AtomicIntegerArray array = new AtomicIntegerArray(5);
        // 直接以数组对象进行构造
        AtomicIntegerArray array1 = new AtomicIntegerArray(new int[]{1, 2, 3, 4, 5});
        // 指定索引指定值
        array.set(0, 100);
        array.set(1, 200);
        array.set(2, 300);
        array.set(3, 400);
        array.set(4, 500);
        // array.set(5,600); // 运行报错

        int i = array.get(3);
        System.out.println(i);
    }
}
