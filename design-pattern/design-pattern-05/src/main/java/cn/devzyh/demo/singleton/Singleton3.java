package cn.devzyh.demo.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 方式三
 * 懒汉模式线程安全
 * 缺点：线程等待时间过长
 */
public class Singleton3 {

    private static Map instance;

    public static synchronized Map getInstance() {
        if (instance == null) {
            instance = new HashMap();
        }
        return instance;
    }

}
