package cn.devzyh.demo.factory.impl;

import cn.devzyh.demo.factory.IRedisAdapter;

/**
 * 腾讯云Redis操作
 */
public class TencentRedisAdapter implements IRedisAdapter {

    @Override
    public Object get(String key) {
        System.out.println("腾讯云get操作实现");
        return null;
    }

    @Override
    public void set(String key, Object val) {
        System.out.println("腾讯云set操作实现");
    }

    @Override
    public void del(String key) {
        System.out.println("腾讯云del操作实现");
    }

}
