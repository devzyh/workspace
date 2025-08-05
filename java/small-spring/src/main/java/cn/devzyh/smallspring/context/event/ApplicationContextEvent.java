package cn.devzyh.smallspring.context.event;

import cn.devzyh.smallspring.context.ApplicationContext;
import cn.devzyh.smallspring.context.ApplicationEvent;

/**
 * 应用上下文事件
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * 构造应用上下文事件
     * @param source
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取应用上下文对象
     *
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
