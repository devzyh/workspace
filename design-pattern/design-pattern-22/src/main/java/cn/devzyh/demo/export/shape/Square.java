package cn.devzyh.demo.export.shape;

import cn.devzyh.demo.export.IShape;
import cn.devzyh.demo.export.Visitor;

public class Square implements IShape {

    @Override
    public void func() {
        System.out.println("我是正方形");
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitSquare(this);
    }

}
