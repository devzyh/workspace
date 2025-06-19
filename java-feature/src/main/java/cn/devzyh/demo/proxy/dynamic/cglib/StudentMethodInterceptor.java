package cn.devzyh.demo.proxy.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理类方法拦截器
 */
public class StudentMethodInterceptor implements MethodInterceptor {

    /**
     * 对被代理对象的方法做具体的调用和增强
     *
     * @param o           被代理的对象
     * @param method      拦截到的方法
     * @param objects     拦截到方法的入参
     * @param methodProxy 方法代理对象：用于调用被代理对象方法和链式调用
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前面干点啥");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("后面干点啥");
        return result;
    }

}
