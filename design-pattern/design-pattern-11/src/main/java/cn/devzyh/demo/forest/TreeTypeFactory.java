package cn.devzyh.demo.forest;

import java.util.HashMap;
import java.util.Map;

/**
 * 树享元对象工厂类
 * 确保一个TreeType的对象只被创建一次达到节约内存的目的
 */
public class TreeTypeFactory {

    private static Map<String, TreeType> treeTypeMap = new HashMap<>();

    /**
     * 获取树类型对象
     *
     * @param name
     * @param type
     * @return
     */
    public static TreeType getTreeType(String name, String type) {
        TreeType tt = treeTypeMap.get(name);
        if (tt == null) {
            tt = new TreeType(name, type);
            treeTypeMap.put(name, tt);
        }
        return tt;
    }
}
