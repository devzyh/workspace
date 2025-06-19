package cn.devzyh.demo.form.mediator;

import cn.devzyh.demo.form.IComponent;
import cn.devzyh.demo.form.IMediator;
import cn.devzyh.demo.form.component.Button;
import cn.devzyh.demo.form.component.Input;
import cn.devzyh.demo.form.component.Select;

/**
 * 表单中介者
 */
public class FormMediator implements IMediator {

    private Button button;
    private Input input;
    private Select select;

    @Override
    public void addComponent(IComponent component) {
        switch (component.getName()) {
            case "button":
                button = (Button) component;
                break;
            case "input":
                input = (Input) component;
                break;
            case "select":
                select = (Select) component;
                break;
            default:
                break;
        }
    }

    @Override
    public void syncValue(String value) {
        button.setValue(value);
        input.setValue(value);
        select.setValue(value);
    }

}
