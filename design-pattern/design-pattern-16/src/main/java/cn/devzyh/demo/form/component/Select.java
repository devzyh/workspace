package cn.devzyh.demo.form.component;

import cn.devzyh.demo.form.IComponent;

/**
 * 下拉框组件
 */
public class Select implements IComponent {
    @Override
    public void setValue(String value) {
        System.out.println("下拉框值设置为：" + value);
    }

    @Override
    public String getName() {
        return "select";
    }
}
