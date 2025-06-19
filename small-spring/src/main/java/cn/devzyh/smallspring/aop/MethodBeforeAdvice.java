package cn.devzyh.smallspring.aop;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * 前置处理器
     *
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
