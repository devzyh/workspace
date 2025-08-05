package cn.devzyh.smallspring.beans.factory.support;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 代理 Bean 对象注册支持
 */
public class FactoryBeanRegisterSupport extends DefaultSingletonBeanRegistry {

    /**
     * 存储自定义获取的单例Bean
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    /**
     * 获取缓存的单例Bean对象
     *
     * @param beanName
     * @return
     */
    protected Object getCacheObjectForFactoryBean(String beanName) {
        Object obj = this.factoryBeanObjectCache.get(beanName);
        return obj == NULL_OBJECT ? null : obj;
    }

    /**
     * 获取单例、非单例Bean对象
     *
     * @param factory
     * @param beanName
     * @return
     */
    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object obj = this.getCacheObjectForFactoryBean(beanName);
            if (obj == null) {
                obj = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, obj == null ? NULL_OBJECT : obj);
            }
            return obj;
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    /**
     * 获取FactoryBean对象
     *
     * @param beanName
     * @return
     */
    private Object doGetObjectFromFactoryBean(final FactoryBean factory, String beanName) {
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("获取FactoryBean内的对象[" + beanName + "]失败：" + e.getMessage());
        }
    }

}
