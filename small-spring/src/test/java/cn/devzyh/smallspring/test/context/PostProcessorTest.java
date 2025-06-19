package cn.devzyh.smallspring.test.context;

import cn.devzyh.smallspring.context.support.ClassPathXmlApplicationContext;
import cn.devzyh.smallspring.test.testbean.UserService;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

public class PostProcessorTest {

    /**
     * 不使用上下文类的测试
     */
    @Test
    public void testWitContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-processor.xml");

        // 使用Bean对象
        UserService service = context.getBean(StrUtil.lowerFirst(UserService.class.getSimpleName()), UserService.class);
        System.out.println("最终name为" + service.getName());
    }

}
