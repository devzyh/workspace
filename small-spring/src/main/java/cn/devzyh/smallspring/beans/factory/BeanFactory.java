package cn.devzyh.smallspring.beans.factory;

import cn.devzyh.smallspring.beans.BeansException;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;
}
