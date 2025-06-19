package cn.devzyh.smallspring.context;

import java.util.EventListener;

/**
 * 应用监听器
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 响应事件处理
     *
     * @param event
     */
    void onApplicationEvent(E event);
}
