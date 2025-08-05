package cn.devzyh.smallspring.test.processor;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.factory.config.BeanPostProcessor;
import cn.devzyh.smallspring.test.testbean.UserService;
import cn.hutool.core.util.StrUtil;

/**
 * 自定义Bean实例化前、后修改器，此时已经开始实例化Bean对象
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!StrUtil.equals(beanName, "userService")) {
            return bean;
        }
        UserService userService = (UserService) bean;
        System.out.println("经过BeanFactoryPostProcessor修改后的name：" + userService.getName());

        userService.setName("postProcessBeforeInitialization-" + userService.getName());
        return userService;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行了" + beanName + "的postProcessAfterInitialization但是我不做任何修改");
        return bean;
    }
}
