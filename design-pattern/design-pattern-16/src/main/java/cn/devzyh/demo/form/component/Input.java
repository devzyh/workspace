package cn.devzyh.demo.form.component;

import cn.devzyh.demo.form.IComponent;

/**
 * 输入框组件
 */
public class Input implements IComponent {
    @Override
    public void setValue(String value) {
        System.out.println("输入框值设置为：" + value);
    }

    @Override
    public String getName() {
        return "input";
    }
}
