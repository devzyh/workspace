package cn.devzyh.demo.export;

import cn.devzyh.demo.export.shape.Circle;
import cn.devzyh.demo.export.shape.Dot;
import cn.devzyh.demo.export.shape.Square;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class IShapeTest {

    @Test
    public void accept() {
        List<IShape> shapeList = new LinkedList<>();
        shapeList.add(new Dot());
        shapeList.add(new Circle());
        shapeList.add(new Square());

        // 遍历图形
        Visitor visitor = new Visitor();
        for (IShape shape : shapeList) {
            System.out.println(shape.accept(visitor));
        }

    }
}