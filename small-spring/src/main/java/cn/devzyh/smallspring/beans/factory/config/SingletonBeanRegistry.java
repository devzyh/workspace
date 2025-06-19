package cn.devzyh.smallspring.beans.factory.config;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 * <p>
 * 单例注册表
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);


    /**
     * 销毁单例对象
     */
    void destroySingletons();

    /**
     * 注册单例对象
     *
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);
}
                                                