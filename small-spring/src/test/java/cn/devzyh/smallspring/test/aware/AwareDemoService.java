package cn.devzyh.smallspring.test.aware;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.factory.BeanClassLoaderAware;
import cn.devzyh.smallspring.beans.factory.BeanFactory;
import cn.devzyh.smallspring.beans.factory.BeanFactoryAware;
import cn.devzyh.smallspring.beans.factory.BeanNameAware;
import cn.devzyh.smallspring.context.ApplicationContext;
import cn.devzyh.smallspring.context.ApplicationContextAware;

/**
 * 演示Aware接口的使用
 */
public class AwareDemoService implements BeanFactoryAware, BeanClassLoaderAware, BeanNameAware, ApplicationContextAware {

    public void hello() {
        System.out.println("Hello World!");
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        System.out.println("当前所属的ClassLoader=" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("当前所属的BeanFactory=" + beanFactory);
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("当前所属的beanName=" + beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("当前所属的ApplicationContext=" + applicationContext);
    }
}
