package cn.devzyh.demo.container;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 并发版Set集合的使用
 */
public class CopyOnWriteArraySetTest {

    public static void main(String[] args) {
        // 基于CopyOnWrite原理实现的Set集合
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        set.add("A"); // 修改会复制一个新数组进行修改，然后将新数组指向给集合
        set.add("B"); // 修改多时会比较占用系统资源CPU与内存等
        set.add("C"); // 基于CopyOnWrite的容器适合读多写少的情况
        set.add("D");
        set.add("E");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println("next = " + next);
            set.add("F"); // 此时修改迭代器不会抛出异常，因为迭代器使用的是循环时数组的快照，修改是在复制的新数组上。
            // 迭代器不会输出F
        }

        // 再次循环输出修改后数据
        System.out.println("修改后数据：");
        set.stream().forEach(System.out::println);
    }
}
