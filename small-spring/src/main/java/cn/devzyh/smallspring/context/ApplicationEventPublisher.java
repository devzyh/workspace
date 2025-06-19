package cn.devzyh.smallspring.context;

/**
 * 事件发布接口
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     *
     * @param event
     */
    void publishEvent(ApplicationEvent event);

}
