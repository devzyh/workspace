package cn.devzyh.smallspring.context.event;

import cn.devzyh.smallspring.beans.factory.BeanFactory;
import cn.devzyh.smallspring.context.ApplicationEvent;
import cn.devzyh.smallspring.context.ApplicationListener;

/**
 * 简单事件广播器
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
