package cn.devzyh.demo.export.shape;

import cn.devzyh.demo.export.IShape;
import cn.devzyh.demo.export.Visitor;

public class Circle implements IShape {

    @Override
    public void func() {
        System.out.println("我是圆形");
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCircle(this);
    }

}
