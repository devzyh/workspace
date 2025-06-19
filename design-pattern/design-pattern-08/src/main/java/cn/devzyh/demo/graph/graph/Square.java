package cn.devzyh.demo.graph.graph;

import cn.devzyh.demo.graph.IGraph;

public class Square implements IGraph {

    @Override
    public void draw() {
        System.out.println("绘制一个正方形");
    }
}