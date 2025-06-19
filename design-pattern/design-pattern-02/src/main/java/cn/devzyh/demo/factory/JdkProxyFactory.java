package cn.devzyh.demo.factory;

import cn.devzyh.demo.factory.handler.RedisInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * 代理类工厂
 * 主要提供获取代理后对象的方法工厂
 */
public class JdkProxyFactory {

    /**
     * 获取Redis工具类代理后的对象
     *
     * @param adapter 被代理的Redis操作类对象，需要实现对应接口
     * @return 代理后的Redis操作类对象
     */
    public static IRedisAdapter getRedisProxy(IRedisAdapter adapter) {
        RedisInvocationHandler handler = new RedisInvocationHandler(adapter);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return (IRedisAdapter) Proxy.newProxyInstance(classLoader, adapter.getClass().getInterfaces(), handler);
    }

}
