package cn.devzyh.demo.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 方式二
 * 懒汉线程不安全实现
 */
public class Singleton2 {

    private static Map instance;

    public static Map getInstance() {
        if (instance == null) {
            instance = new HashMap();
        }
        return instance;
    }

}
