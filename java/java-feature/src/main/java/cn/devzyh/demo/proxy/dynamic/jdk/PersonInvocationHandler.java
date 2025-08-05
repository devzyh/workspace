package cn.devzyh.demo.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类调用处理器
 * 定义代理的具体调用方式
 */
public class PersonInvocationHandler implements InvocationHandler {

    /**
     * 被代理的目标对象
     */
    private final IPersonService target;

    /**
     * 将被代理对象传递给调用处理器
     *
     * @param target
     */
    public PersonInvocationHandler(IPersonService target) {
        this.target = target;
    }

    /**
     * @param proxy  表示当前代理对象的实例
     * @param method 当前调用的方法
     * @param args   当前方法的入参
     * @return 方法返回结果
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        /**
         * 对调用自己的方法进行一个特殊处理
         * 这里只是为了演示proxy参数的使用
         */
        if (method.getName().equals("callSelf")) {
            System.out.println("连续调用自己中");
            method.invoke(target, args);
            return proxy;
        }

        /**
         * 其他方法的调用
         */
        System.out.println("在什么之前");
        Object result = method.invoke(target, args);
        System.out.println("在什么之后");
        return result;
    }

}
