package cn.devzyh.smallspring.test.aware;

import cn.devzyh.smallspring.context.support.ClassPathXmlApplicationContext;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

public class AwareTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aware.xml");
        AwareDemoService service = context.getBean(StrUtil.lowerFirst(AwareDemoService.class.getSimpleName()), AwareDemoService.class);
        service.hello();
    }
}
