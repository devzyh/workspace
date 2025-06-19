package cn.devzyh.demo.graph.graph;

import cn.devzyh.demo.graph.IGraph;

/**
 * 圆形
 */
public class Circle implements IGraph {

    @Override
    public void draw() {
        System.out.println("绘制一个圆形");
    }
}
