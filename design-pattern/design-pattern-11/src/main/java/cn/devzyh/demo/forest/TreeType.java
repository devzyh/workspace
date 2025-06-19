package cn.devzyh.demo.forest;

/**
 * 树的共享属性（享元对象）
 * 属性初始化后不可变
 */
public class TreeType {

    // 名称
    private String name;

    // 科目
    private String type;

    public TreeType(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
