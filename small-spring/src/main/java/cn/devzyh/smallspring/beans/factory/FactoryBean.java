package cn.devzyh.smallspring.beans.factory;

import cn.devzyh.smallspring.beans.BeansException;

public interface FactoryBean<T> {

    /**
     * 获取对象
     *
     * @return
     * @throws BeansException
     */
    T getObject() throws BeansException;

    /**
     * 获取对象类型
     *
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否单例对象
     *
     * @return
     */
    boolean isSingleton();
}
