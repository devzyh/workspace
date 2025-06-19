package cn.devzyh.demo.graph.graph;

import cn.devzyh.demo.graph.IGraph;

import java.util.LinkedList;
import java.util.List;

/**
 * 组合图形
 */
public class CompositeGraph implements IGraph {

    /**
     * 组合图形名称
     */
    private String name;

    /**
     * 包含的图形
     */
    private List<IGraph> children = new LinkedList<>();

    public CompositeGraph(IGraph... children) {
        this("", children);
    }

    public CompositeGraph(String name, IGraph... children) {
        this.name = name;
        for (IGraph child : children) {
            this.children.add(child);
        }
    }

    /**
     * 添加图形
     *
     * @param graph
     */
    void addGraph(IGraph graph) {
        this.children.add(graph);
    }

    /**
     * 清空图形
     */
    void clear() {
        this.children.clear();
    }


    @Override
    public void draw() {
        System.out.printf("下面是组合图形[%s]包含的图形：\n", this.name);
        for (IGraph child : this.children) {
            System.out.println("'''''''''''''''''''''''''''''");
            child.draw();
            System.out.println("'''''''''''''''''''''''''''''");
        }
        System.out.println("-----------------------------");
    }
}
