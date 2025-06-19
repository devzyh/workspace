package cn.devzyh.smallspring.test.aop.bean;

import cn.devzyh.smallspring.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 测试类的方法前置拦截器
 */
public class TestServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("我是方法前置处理器内容");
    }
}
