package cn.devzyh.demo.forest;

public class Tree {

    // 树基本信息
    private TreeType treeType;

    // 树在森林里的坐标信息
    private int x;
    private int y;

    public Tree(TreeType treeType, int x, int y) {
        this.treeType = treeType;
        this.x = x;
        this.y = y;
    }

    /**
     * 绘制树
     */
    public void draw() {
        System.out.printf("[%s]类的[%s]{%s}树位于森林的坐标[%d, %d]\n",
                treeType.getType(), treeType.getName(), treeType.toString(), x, y);
    }

}
