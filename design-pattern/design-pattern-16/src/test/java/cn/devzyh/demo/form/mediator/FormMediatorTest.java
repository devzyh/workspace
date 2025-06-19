package cn.devzyh.demo.form.mediator;

import cn.devzyh.demo.form.IMediator;
import cn.devzyh.demo.form.component.Button;
import cn.devzyh.demo.form.component.Input;
import cn.devzyh.demo.form.component.Select;
import org.junit.Test;

public class FormMediatorTest {

    @Test
    public void syncValue() {
        IMediator formMediator = new FormMediator();
        formMediator.addComponent(new Button());
        formMediator.addComponent(new Input());
        formMediator.addComponent(new Select());

        formMediator.syncValue("Test");
    }
}