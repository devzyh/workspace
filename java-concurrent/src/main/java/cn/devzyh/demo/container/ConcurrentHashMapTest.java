package cn.devzyh.demo.container;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发版Map集合的使用
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        // 简单的使用测试
        ConcurrentHashMap<Object, Object> mapTest = new ConcurrentHashMap<>();
        mapTest.put("test", "info");
        System.out.println(mapTest.get("test"));

        // 在迭代中修改 ConcurrentHashMap， HashMap 中不可以这样操作
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("A", "张三");
        map.put("B", "李四");
        map.put("C", "王五");
        map.put("D", "赵六");
        map.put("E", "钱七");

        Enumeration<String> keys = map.keys();
        while (keys.hasMoreElements()) {
            System.out.println(map.get(keys.nextElement()));
            map.remove("E");
        }
    }
}
