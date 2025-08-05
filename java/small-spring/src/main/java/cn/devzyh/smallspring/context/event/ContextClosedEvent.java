package cn.devzyh.smallspring.context.event;

/**
 * 应用上下文关闭事件
 */
public class ContextClosedEvent extends ApplicationContextEvent {

    /**
     * 构造一个上下文关闭事件
     *
     * @param source
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
