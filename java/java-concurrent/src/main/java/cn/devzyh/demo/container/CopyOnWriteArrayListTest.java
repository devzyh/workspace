package cn.devzyh.demo.container;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发版List集合的使用
 */
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // 此时迭代器获取的是修改前的对象
        Iterator<Integer> it1 = list.iterator();

        // 修改 list
        list.add(4);

        // 此时迭代器获取的是修改后的对象
        Iterator<Integer> it2 = list.iterator();

        // CopyOnWrite 容器修改时会复制原先对象成为副本，在副本上修改后将副本指向给容器
        // 此并发容器 只有写写才会导致线程上锁阻塞 其他的都不会上锁

        System.out.println("it1:");
        it1.forEachRemaining(System.out::println);

        System.out.println("it2:");
        it2.forEachRemaining(System.out::println);
    }
}
