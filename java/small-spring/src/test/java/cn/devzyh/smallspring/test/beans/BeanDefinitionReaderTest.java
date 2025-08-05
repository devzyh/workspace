package cn.devzyh.smallspring.test.beans;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.factory.support.BeanDefinitionReader;
import cn.devzyh.smallspring.beans.factory.support.DefaultListableBeanFactory;
import cn.devzyh.smallspring.beans.factory.xml.XmlBeanDefinitionReader;
import cn.devzyh.smallspring.test.testbean.UserService;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

public class BeanDefinitionReaderTest {

    /**
     * XML配置定义读取
     */
    @Test
    public void testXmlBeanDefinitionReader() throws BeansException {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        // 加载Bean定义
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 使用Bean对象
        UserService service = (UserService) factory.getBean(StrUtil.lowerFirst(UserService.class.getSimpleName()));
    }
}
