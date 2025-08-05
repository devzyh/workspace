package cn.devzyh.smallspring.test.aop;

import cn.devzyh.smallspring.aop.AdvisedSupport;
import cn.devzyh.smallspring.aop.TargetSource;
import cn.devzyh.smallspring.aop.aspectj.AspectJExpressionPointcut;
import cn.devzyh.smallspring.aop.framework.Cglib2AopProxy;
import cn.devzyh.smallspring.aop.framework.JdkDynamicAopProxy;
import cn.devzyh.smallspring.context.support.ClassPathXmlApplicationContext;
import cn.devzyh.smallspring.test.aop.bean.ITestService;
import cn.devzyh.smallspring.test.aop.bean.TestService;
import cn.devzyh.smallspring.test.aop.bean.TestServiceInterceptor;
import org.junit.jupiter.api.Test;

public class AopTest {

    /**
     * AOP功能测试
     */
    @Test
    public void testAop() {
        ITestService testService = new TestService();
        AdvisedSupport advised = new AdvisedSupport();
        advised.setMethodInterceptor(new TestServiceInterceptor());
        advised.setTargetSource(new TargetSource(testService));
        advised.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.devzyh.smallspring.test.aop.bean.ITestService.*(..))"));

        ITestService jdkProxy = (ITestService) new JdkDynamicAopProxy(advised).getProxy();
        System.out.println("JDK代理实现测试结果");
        jdkProxy.hello();

        ITestService cglibProxy = (ITestService) new Cglib2AopProxy(advised).getProxy();
        System.out.println("Cglib代理实现测试结果");
        cglibProxy.hello1();
    }

    /**
     * AOP融合IOC
     */
    @Test
    public void testAopAndIOC() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aop.xml");
        ITestService testService = context.getBean("testService", ITestService.class);
        testService.hello();
    }
}
