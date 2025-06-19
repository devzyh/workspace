package cn.devzyh.smallspring.context.event;

import cn.devzyh.smallspring.context.ApplicationEvent;
import cn.devzyh.smallspring.context.ApplicationListener;

/**
 * 应用事件广播器接口
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加事件监听器
     *
     * @param listener
     */
    void addApplicationEvent(ApplicationListener<?> listener);

    /**
     * 移除事件监听器
     *
     * @param listener
     */
    void removeApplicationEvent(ApplicationListener<?> listener);

    /**
     * 广播事件
     *
     * @param event
     */
    void multicastEvent(ApplicationEvent event);

}
