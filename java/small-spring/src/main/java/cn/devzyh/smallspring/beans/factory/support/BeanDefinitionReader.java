package cn.devzyh.smallspring.beans.factory.support;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.core.io.Resource;
import cn.devzyh.smallspring.core.io.ResourceLoader;

/**
 * Simple interface for bean definition readers.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}
