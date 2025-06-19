package cn.devzyh.demo.form;

/**
 * 界面组件接口
 */
public interface IComponent {

    /**
     * 设置值
     *
     * @param value
     */
    void setValue(String value);

    /**
     * 获取组件名称
     *
     * @return
     */
    String getName();
}
