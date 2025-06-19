package cn.devzyh.demo.export;

/**
 * 图形接口
 */
public interface IShape {

    /**
     * 其他功能
     */
    void func();

    /**
     * 遍历方法
     *
     * @param visitor
     */
    String accept(Visitor visitor);
}
