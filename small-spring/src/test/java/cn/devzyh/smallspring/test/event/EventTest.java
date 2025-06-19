package cn.devzyh.smallspring.test.event;

import cn.devzyh.smallspring.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;

/**
 * 事件测试
 */
public class EventTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-event.xml");
        context.close();
    }
}
