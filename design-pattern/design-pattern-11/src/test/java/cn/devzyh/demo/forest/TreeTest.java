package cn.devzyh.demo.forest;

import org.junit.Test;

public class TreeTest {

    @Test
    public void draw() {
        // 创建树信息
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                TreeType tt1 = TreeTypeFactory.getTreeType("松树", "针叶");
                TreeType tt2 = TreeTypeFactory.getTreeType("地柏", "阔叶");
                Tree t = new Tree(j % 2 == 0 ? tt1 : tt2, i, j);
                t.draw();
            }
        }
    }
}