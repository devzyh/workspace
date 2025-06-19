package cn.devzyh.smallspring.context.event;

/**
 * 应用上下文刷新事件
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

    /**
     * 构造一个上下文刷新事件
     *
     * @param source
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
