package cn.devzyh.smallspring.beans.factory;

import cn.devzyh.smallspring.beans.BeansException;

/**
 * 实现此接口，可以感知到BeanFactory
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
