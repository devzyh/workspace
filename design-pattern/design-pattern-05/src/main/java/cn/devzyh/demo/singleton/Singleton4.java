package cn.devzyh.demo.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 方式四
 * 恶汉模式线程安全
 * 缺点：不管用不用都要加载占内存，方式一的变体
 */
public class Singleton4 {

    public static Map instance = new HashMap();

    public static Map getInstance() {
        return instance;
    }

}
