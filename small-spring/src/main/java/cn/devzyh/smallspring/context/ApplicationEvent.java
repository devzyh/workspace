package cn.devzyh.smallspring.context;

import java.util.EventObject;

/**
 * 应用事件抽象类
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * 构造一个事件
     *
     * @param source
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
