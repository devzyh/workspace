package cn.devzyh.demo.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 方式一
 * 类静态变量实现单例
 * 仅用于全局访问
 */
public class Singleton1 {

    public static Map INSTANCE = new HashMap();

}
