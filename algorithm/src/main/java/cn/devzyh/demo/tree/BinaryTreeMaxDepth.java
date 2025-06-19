package cn.devzyh.demo.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大深度
 */
public class BinaryTreeMaxDepth {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(3);
        root.left = new BinaryTreeNode(9);
        root.right = new BinaryTreeNode(20);
        root.right.left = new BinaryTreeNode(15);
        root.right.right = new BinaryTreeNode(7);

        System.out.println(breadthFirstSearch(root));

        System.out.println(depthFirstSearch(root));

    }

    /**
     * 广度优先搜索
     * 时间：O(n)
     * 空间：O(n)
     *
     * @param root
     * @return
     */
    static int breadthFirstSearch(BinaryTreeNode root) {
        // 空节点直接返回0
        if (root == null) {
            return 0;
        }

        // 队列存储未遍历节点
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        int depth = 0;

        // 根节点入队
        queue.offer(root);

        // 队列不为空进行遍历
        while (!queue.isEmpty()) {
            // 记录当前层级节点数量作为当前层级遍历标志
            int size = queue.size();
            while (size > 0) {
                // 取出队头当前层级节点
                BinaryTreeNode node = queue.poll();
                // 若存在子节点则入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // 递减当前层级节点数量
                size--;
            }
            // 遍历完一个层级则进行深度递增
            depth++;
        }

        // 返回最终深度
        return depth;
    }


    /**
     * 深度优先搜索
     * 时间：O(n) n:树的节点数量
     * 空间：O(h) h:树的深度
     *
     * @param node
     * @return
     */
    static int depthFirstSearch(BinaryTreeNode node) {
        // 等价表达式 入参：二叉树节点 出参：树的深度
        // 节点为空返回0 （递归退出条件）
        if (node == null) {
            return 0;
        }

        // （递归函数功能）
        // 计算左子树深度
        int leftH = depthFirstSearch(node.left);

        // 计算右子树深度
        int rightH = depthFirstSearch(node.right);

        // 返回左右子树深度的较大值并+1
        return Math.max(leftH, rightH) + 1;
    }
}
