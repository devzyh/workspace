package cn.devzyh.demo.export.shape;

import cn.devzyh.demo.export.IShape;
import cn.devzyh.demo.export.Visitor;

public class Dot implements IShape {

    @Override
    public void func() {
        System.out.println("我是点");
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitDot(this);
    }

}
