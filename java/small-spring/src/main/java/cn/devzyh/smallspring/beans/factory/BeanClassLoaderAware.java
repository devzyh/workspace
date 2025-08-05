package cn.devzyh.smallspring.beans.factory;

/**
 * 实现此接口，可以感知到所属的ClassLoader
 */
public interface BeanClassLoaderAware extends Aware {

    void setClassLoader(ClassLoader classLoader);

}
