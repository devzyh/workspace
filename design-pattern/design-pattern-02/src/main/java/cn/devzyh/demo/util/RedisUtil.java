package cn.devzyh.demo.util;

import cn.devzyh.demo.factory.IRedisAdapter;

/**
 * 改造前的Redis工具类
 */
public class RedisUtil implements IRedisAdapter {

    @Override
    public Object get(String key) {
        System.out.println("改造前get方法实现");
        return null;
    }

    @Override
    public void set(String key, Object val) {
        System.out.println("改造前set方法实现");
    }

    @Override
    public void del(String key) {
        System.out.println("改造前del方法实现");
    }

}
