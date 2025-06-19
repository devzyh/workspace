package cn.devzyh.smallspring.beans.factory;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.factory.config.AutowireCapableBeanFactory;
import cn.devzyh.smallspring.beans.factory.config.BeanDefinition;
import cn.devzyh.smallspring.beans.factory.config.ConfigurableBeanFactory;

/**
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
