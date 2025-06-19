package cn.devzyh.smallspring.test.beans;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.PropertyValue;
import cn.devzyh.smallspring.beans.PropertyValues;
import cn.devzyh.smallspring.beans.factory.config.BeanDefinition;
import cn.devzyh.smallspring.beans.factory.config.BeanReference;
import cn.devzyh.smallspring.beans.factory.support.DefaultListableBeanFactory;
import cn.devzyh.smallspring.test.testbean.UserDao;
import cn.devzyh.smallspring.test.testbean.UserService;
import org.junit.jupiter.api.Test;

public class BeanTest {

    /**
     * Bean工厂测试
     */
    @Test
    public void testBeanFactory() throws BeansException {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition definition = new BeanDefinition(UserService.class);
        String beanName = "userService";
        factory.registerBeanDefinition(beanName, definition);

        UserService service1 = (UserService) factory.getBean(beanName);
        service1.hello();

        UserService service2 = (UserService) factory.getBean(beanName);
        service2.hello();
    }

    /**
     * 带有构造参数的Bean对象实例化
     *
     * @throws BeansException
     */
    @Test
    public void testBeanWithArgs() throws BeansException {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition definition = new BeanDefinition(UserService.class);
        String beanName = "userService";
        factory.registerBeanDefinition(beanName, definition);

        UserService service = (UserService) factory.getBean(beanName, "zhangsan");
        service.say();
    }

    /**
     * Bean对象属性依赖
     *
     * @throws BeansException
     */
    @Test
    public void testBeanApplyPropertyValues() throws BeansException {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        BeanDefinition definition = new BeanDefinition(UserDao.class);
        String daoName = "userDao";
        factory.registerBeanDefinition(daoName, definition);

        definition = new BeanDefinition(UserService.class);
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference(daoName)));
        propertyValues.addPropertyValue(new PropertyValue("name", "张三"));
        definition.setPropertyValues(propertyValues);
        String svcName = "userService";
        factory.registerBeanDefinition(svcName, definition);

        UserService service = (UserService) factory.getBean(svcName);
        service.printAddress();
    }

}
