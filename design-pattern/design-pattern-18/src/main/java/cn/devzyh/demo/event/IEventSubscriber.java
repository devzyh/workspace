package cn.devzyh.demo.event;

/**
 * 事件订阅者接口
 */
public interface IEventSubscriber {

    /**
     * 事件响应逻辑
     *
     * @param event  事件类型
     * @param params 事件参数
     */
    void update(String event, Object params);
}
