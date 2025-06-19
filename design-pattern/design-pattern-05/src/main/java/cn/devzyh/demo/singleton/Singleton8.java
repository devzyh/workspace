package cn.devzyh.demo.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 方式八
 * 枚举实现单例（这里演示将一个已有类改造成枚举单例，如果是新的类直接在枚举方法里实现功能）
 * 优点：线程安全、自由串行化、单一实例
 * 缺点：继承场景下是不可用
 */
public enum Singleton8 {

    INSTANCE;

    private Map instance;

    Singleton8() {
        instance = new HashMap();
    }

    public Map getInstance() {
        return instance;
    }

}
