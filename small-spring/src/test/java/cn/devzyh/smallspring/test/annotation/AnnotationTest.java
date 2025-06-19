package cn.devzyh.smallspring.test.annotation;

import cn.devzyh.smallspring.context.support.ClassPathXmlApplicationContext;
import cn.devzyh.smallspring.test.annotation.bean.ITestService;
import org.junit.jupiter.api.Test;

/**
 * 注解使用测试
 */
public class AnnotationTest {

    /**
     * 属性测试
     */
    @Test
    public void testProperty() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        ITestService testService = context.getBean("testService", ITestService.class);
        testService.hello();
    }

    /**
     * 扫描注解测试
     */
    @Test
    public void testScan() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        ITestService testService = context.getBean("testService", ITestService.class);
        testService.hello();
    }

    /**
     * 测试注解
     */
    @Test
    public void testAnnotation() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-annotation.xml");
        ITestService testService = context.getBean("testService", ITestService.class);
        testService.printName();
    }
}
