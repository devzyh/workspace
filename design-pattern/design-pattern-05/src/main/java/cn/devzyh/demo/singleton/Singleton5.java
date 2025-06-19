package cn.devzyh.demo.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 方式五
 * 静态内部类线单例（推荐）
 * 优点：线程安全、懒加载、无锁
 * 因为JVM虚拟机可以保证多线程并发访问的正确性，也就是一个类的构造方法在多线程环境下可以被正确的加载
 */
public class Singleton5 {

    private static class SingletonHolder {
        private static Map instance = new HashMap();
    }

    public static Map getInstance() {
        return SingletonHolder.instance;
    }

}
