package cn.devzyh.smallspring.context.support;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.factory.config.BeanPostProcessor;
import cn.devzyh.smallspring.context.ApplicationContext;
import cn.devzyh.smallspring.context.ApplicationContextAware;

/**
 * 使用BeanPostProcessor将ApplicationContext带入
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private ApplicationContext context;

    /**
     * ApplicationContext执行refresh时传入对象
     *
     * @param context
     */
    public ApplicationContextAwareProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(this.context);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
