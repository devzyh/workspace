package cn.devzyh.smallspring.test.aop.bean;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 自定义Test类拦截器
 */
public class TestServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            System.out.println("监控 - Begin");
            System.out.println("方法名称：" + methodInvocation.getMethod());
            return methodInvocation.proceed();
        } finally {
            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控 - End\r\n");
        }
    }
}
