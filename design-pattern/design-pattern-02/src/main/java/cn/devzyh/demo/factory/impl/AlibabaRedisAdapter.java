package cn.devzyh.demo.factory.impl;

import cn.devzyh.demo.factory.IRedisAdapter;


/**
 * 阿里云Redis操作
 */
public class AlibabaRedisAdapter implements IRedisAdapter {
    
    @Override
    public Object get(String key) {
        System.out.println("阿里云get操作实现");
        return null;
    }

    @Override
    public void set(String key, Object val) {
        System.out.println("阿里云set操作实现");
    }

    @Override
    public void del(String key) {
        System.out.println("阿里云del操作实现");
    }
    
}
