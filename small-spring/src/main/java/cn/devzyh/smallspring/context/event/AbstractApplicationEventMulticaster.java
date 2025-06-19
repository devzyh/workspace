package cn.devzyh.smallspring.context.event;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.factory.BeanFactory;
import cn.devzyh.smallspring.beans.factory.BeanFactoryAware;
import cn.devzyh.smallspring.context.ApplicationEvent;
import cn.devzyh.smallspring.context.ApplicationListener;
import cn.devzyh.smallspring.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 应用事件广播器抽象类
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationEvent(ApplicationListener<?> listener) {
        this.applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationEvent(ApplicationListener<?> listener) {
        this.applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 获取事件的所有监听器
     *
     * @param event
     * @return
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : this.applicationListeners) {
            if (supportEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * 判断监听器是否关注了指定事件
     *
     * @param listener
     * @param event
     * @return
     */
    private boolean supportEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        // 获取事件参数类型
        Class<? extends ApplicationListener> listenerClass = listener.getClass();

        // 按照不同实例化对象类型获取对象类型
        Class<?> targetClass = listenerClass;
        if (ClassUtils.isCglibProxyClass(targetClass)) {
            targetClass = targetClass.getSuperclass();
        }
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        // 获取泛型参数类型
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClass;
        try {
            eventClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("错误的事件类型：" + className);
        }

        return eventClass.isAssignableFrom(event.getClass());
    }

}
