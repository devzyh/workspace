package cn.devzyh.demo.form;

/**
 * 中介者接口
 */
public interface IMediator {

    /**
     * 新增组件
     *
     * @param component
     */
    void addComponent(IComponent component);

    /**
     * 同步组件值
     *
     * @param value
     */
    void syncValue(String value);
}
