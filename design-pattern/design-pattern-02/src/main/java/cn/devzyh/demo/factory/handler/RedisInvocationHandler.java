package cn.devzyh.demo.factory.handler;

import cn.devzyh.demo.factory.IRedisAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Redis代理类调用实现
 * 除了调用代理前的对象方法外还可以顺道做一些方法增强，比如记个日志，完成产品的XX需求等。
 */
public class RedisInvocationHandler implements InvocationHandler {

    private final IRedisAdapter target;

    public RedisInvocationHandler(IRedisAdapter target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前可以做一些事情");
        Object result = method.invoke(target, args);
        System.out.println("调用后也可以做一些事情");
        return result;
    }

}
