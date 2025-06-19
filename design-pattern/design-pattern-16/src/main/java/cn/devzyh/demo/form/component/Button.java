package cn.devzyh.demo.form.component;

import cn.devzyh.demo.form.IComponent;

/**
 * 按钮组件
 */
public class Button implements IComponent {
    @Override
    public void setValue(String value) {
        System.out.println("按钮值设置为：" + value);
    }

    @Override
    public String getName() {
        return "button";
    }
}
