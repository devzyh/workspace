package cn.devzyh.smallspring.test.cyclicdenpendce;

import cn.devzyh.smallspring.context.support.ClassPathXmlApplicationContext;
import cn.devzyh.smallspring.test.cyclicdenpendce.bean.Husband;
import cn.devzyh.smallspring.test.cyclicdenpendce.bean.Wife;
import org.junit.jupiter.api.Test;

/**
 * 循环依赖测试
 */
public class CyclicTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-cyclic.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println("老公的媳妇：" + husband.queryWife());
        System.out.println("媳妇的老公：" + wife.queryHusband());
    }
}
