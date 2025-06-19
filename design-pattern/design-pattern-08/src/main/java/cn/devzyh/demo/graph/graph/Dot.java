package cn.devzyh.demo.graph.graph;

import cn.devzyh.demo.graph.IGraph;

public class Dot implements IGraph {

    @Override
    public void draw() {
        System.out.println("绘制一个点");
    }
}