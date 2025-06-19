package cn.devzyh.smallspring.test.factorybean;

import cn.devzyh.smallspring.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;

public class FactoryBeanTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-factory-bean.xml");
        context.registerShutdownHook();

        NameService nameService1 = context.getBean("nameService", NameService.class);
        NameService nameService2 = context.getBean("nameService", NameService.class);

        // 演示原型模式对象
        nameService1.printArea();
        nameService2.printArea();

        // 演示单例模式和被代理对象
        nameService1.printFirstName();
        nameService2.printFirstName();
    }
}
