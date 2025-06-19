package cn.devzyh.demo.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 方式七
 * CAS锁单例
 * 优点：线程安全、懒加载
 * 缺点：并发访问高了等待时间会很久
 */
public class Singleton7 {

    public static AtomicReference<Map> atomicInstance = new AtomicReference<>();

    public static Map getInstance() {
        while (true) {
            Map instance = atomicInstance.get();
            if (instance != null) {
                return instance;
            }
            atomicInstance.compareAndSet(null, new HashMap());
            return atomicInstance.get();
        }
    }

}
