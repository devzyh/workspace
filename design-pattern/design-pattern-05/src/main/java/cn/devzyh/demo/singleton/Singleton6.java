package cn.devzyh.demo.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 方式六
 * 双重检测锁单例
 * 优点：懒加载、线程安全、方式三的优化
 */
public class Singleton6 {

    public static Map instance;

    public static Map getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    synchronized (Singleton6.class) {
                        instance = new HashMap();
                    }
                }
            }
        }

        return instance;
    }

}
