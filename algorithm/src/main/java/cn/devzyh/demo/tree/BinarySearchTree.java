package cn.devzyh.demo.tree;

import cn.devzyh.demo.tree.ds.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {

    private TreeNode<Integer> root;
    private int maxBstSum = 0; // 最大二叉搜索子树和

    public void insert(int val) {
        TreeNode<Integer> res = insert(this.root, val);
        if (this.root == null) {
            this.root = res;
        }
    }

    /**
     * 新增数据
     * 时间：O(h) h=栈的深度
     * 空间：O(n)
     *
     * @param root
     * @param val
     * @return
     */
    private TreeNode<Integer> insert(TreeNode<Integer> root, int val) {
        if (root == null) {  // 节点为空则新建节点
            return new TreeNode<>(val);
        } else if (val < root.val) { // 小于根节点放入左子树
            root.left = insert(root.left, val);
        } else { // 否则放入右子树
            root.right = insert(root.right, val);
        }
        return root;
    }

    /**
     * 递归实现的二叉树搜索
     * 时间：O(h) h=树的深度
     * 空间：O(h) h=树的深度
     *
     * @param target
     * @return
     */
    private boolean searchByRecursion(int target) {
        return searchByRecursion(this.root, target);
    }

    private boolean searchByRecursion(TreeNode<Integer> root, int target) {
        // 找到空节点为止，则直接返回不存在
        if (root == null) {
            return false;
        }

        boolean res;
        // 当前节点数据和目标值相同则存在
        if (root.val == target) {
            res = true;
        }

        // 节点值小于目标值则查找右子树
        if (root.val < target) {
            res = searchByRecursion(root.right, target);
        } else { // 节点值大于目标值则查找左子树
            res = searchByRecursion(root.left, target);
        }

        return res;
    }

    /**
     * 循环实现的二叉树搜索
     * 时间：O(h) h=树的深度
     * 空间：O(1)
     *
     * @param target
     * @return
     */
    private boolean searchByIteration(int target) {
        TreeNode<Integer> cur = this.root;

        while (cur != null) {
            // 找到相同值则返回存在
            if (cur.val == target) {
                return true;
            }
            // 当前节点值小于目标值则查找右子树
            if (cur.val < target) {
                cur = cur.right;
            } else { // 当前节点值大于目标值则查找左子树
                cur = cur.left;
            }
        }

        // 未找到直接返回不存
        return false;
    }

    /**
     * 验证当前树是否是二叉搜索子树
     * 判定标准：中序遍历下结果是不是升序排列
     *
     * @param root
     * @param result
     */
    void verifyBst(TreeNode<Integer> root, BstResult result) {
        // 空节点是二叉搜索子树
        if (root == null) {
            result.isBst = true;
            return;
        }

        // 先遍历左子树
        verifyBst(root.left, result);

        // 对当前遍历节点进行处理
        // 当前节点值小于等于上一个节点值则不是二叉搜索子树
        // 左子树不是二叉搜索子树也返回
        if (root.val <= result.prevVal || !result.isBst) {
            result.isBst = false;
            result.bstSum = 0;
            return;
        }

        result.prevVal = root.val; // 记录当前节点值留作下次判断使用
        result.bstSum += root.val; // 计算二叉搜索子树键值和

        // 在遍历右子树
        verifyBst(root.right, result);
    }


    /**
     * 获取最大二叉搜索树键值和
     *
     * @return
     */
    public int maxBstSum() {
        return maxBstSum(this.root);
    }

    private int maxBstSum(TreeNode<Integer> root) {
        if (root == null) {
            return Math.max(this.maxBstSum, 0);
        }

        // 计算当前节点二叉搜索子树键值和
        BstResult result = new BstResult();
        verifyBst(root, result);
        if (result.isBst) {
            this.maxBstSum = Math.max(this.maxBstSum, result.bstSum);
        }

        // 遍历左子树
        maxBstSum(root.left);

        // 遍历右子树
        maxBstSum(root.right);

        return this.maxBstSum;
    }


    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(3, 2, 1, 6, 5, 4);
        BinarySearchTree bst = new BinarySearchTree();
        for (Integer num : nums) {
            bst.insert(num);
        }
        System.out.println(bst);

        System.out.println(bst.searchByRecursion(3));
        System.out.println(bst.searchByRecursion(31));
        System.out.println(bst.searchByIteration(6));
        System.out.println(bst.searchByIteration(61));

        BstResult result = new BstResult();
        bst.verifyBst(bst.root, result);
        System.out.println(result.isBst);
        System.out.println(result.bstSum);

        System.out.println(bst.maxBstSum());
    }
}

/**
 * 二叉搜索子树遍历结果
 */
class BstResult {
    boolean isBst = false; // 当前子树是不是二叉搜索子树
    int prevVal = Integer.MIN_VALUE; // 上一个访问节点的值
    int bstSum = 0; // 二叉搜索子树键值和
}
