package cn.devzyh.smallspring.beans.factory;


import cn.devzyh.smallspring.beans.BeansException;

/**
 * Defines a factory which can return an Object instance
 * (possibly shared or independent) when invoked.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
