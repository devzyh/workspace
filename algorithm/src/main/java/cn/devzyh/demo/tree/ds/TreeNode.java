package cn.devzyh.demo.tree.ds;

/**
 * 二叉树节点
 */
public class TreeNode<T> {
    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T val) {
        this.val = val;
    }
}
