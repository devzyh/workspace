package cn.devzyh.smallspring.test.processor;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.PropertyValue;
import cn.devzyh.smallspring.beans.PropertyValues;
import cn.devzyh.smallspring.beans.factory.ConfigurableListableBeanFactory;
import cn.devzyh.smallspring.beans.factory.config.BeanDefinition;
import cn.devzyh.smallspring.beans.factory.config.BeanFactoryPostProcessor;

/**
 * 自定义Bean定义修改器；此时刚加载完Bean定义，未进入Bean对象实例化
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition definition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = definition.getPropertyValues();

        // 将初始化的name改为new李四
        propertyValues.addPropertyValue(new PropertyValue("name", "MyBeanFactoryPostProcessor-李四"));
    }
}
