package cn.devzyh.demo.graph;

import cn.devzyh.demo.graph.graph.Circle;
import cn.devzyh.demo.graph.graph.CompositeGraph;
import cn.devzyh.demo.graph.graph.Dot;
import cn.devzyh.demo.graph.graph.Square;
import org.junit.Test;

public class IGraphTest {

    @Test
    public void draw() {
        // 绘制一个简单图形
        IGraph sg = new Circle();
        sg.draw();

        System.out.println("===================================");

        // 绘制一个组合图形
        IGraph cg = new CompositeGraph(
                "Max",
                new Dot(),
                new Square(),
                new CompositeGraph("Inner",
                        new Circle(),
                        new Square(),
                        new CompositeGraph("Min",
                                new Dot()))
        );

        cg.draw();
    }
}