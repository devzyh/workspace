package cn.devzyh.smallspring.test.beans;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;

public class BeanInitDestroyTest {

    /**
     * Bean初始和销毁方法测试
     */
    @Test
    public void test() throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-init-destroy.xml");
        context.close();
    }

}
