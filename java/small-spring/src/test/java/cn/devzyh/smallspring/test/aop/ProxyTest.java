package cn.devzyh.smallspring.test.aop;

import cn.devzyh.smallspring.aop.MethodMatcher;
import cn.devzyh.smallspring.aop.aspectj.AspectJExpressionPointcut;
import cn.devzyh.smallspring.aop.framework.ReflectiveMethodInvocation;
import cn.devzyh.smallspring.test.aop.bean.ITestService;
import cn.devzyh.smallspring.test.aop.bean.TestService;
import cn.devzyh.smallspring.test.testbean.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理使用测试
 */
public class ProxyTest {

    @Test
    public void test() {
        // 被代理的目标对象
        Object target = new TestService();

        // AOP代理
        ITestService proxy = (ITestService) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    // 方法过滤器
                    MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* cn.devzyh.smallspring.test.aop.bean.ITestService.*(..))");

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 不在拦截的方法范围内，直接调用原始方法
                        if (!methodMatcher.matches(method, target.getClass())) {
                            return method.invoke(target, args);
                        }

                        // 方法拦截器
                        MethodInterceptor methodInterceptor = invocation -> {
                            long start = System.currentTimeMillis();
                            try {
                                System.out.println("监控 - Begin");
                                System.out.println("方法名称：" + invocation.getMethod().getName());
                                return invocation.proceed();
                            } finally {
                                System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                                System.out.println("监控 - End\r\n");
                            }
                        };
                        // 反射调用
                        return methodInterceptor.invoke(new ReflectiveMethodInvocation(target, method, args));
                    }
                });

        proxy.hello();
    }

    /**
     * AOP切点判断测试
     */
    @Test
    public void testAop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* cn.devzyh.smallspring.test.aop.bean.ITestService.*(..))");
        Class clazz = TestService.class;
        Method method = clazz.getDeclaredMethod("hello");
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }
}
