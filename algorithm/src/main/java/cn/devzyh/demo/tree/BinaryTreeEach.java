package cn.devzyh.demo.tree;

import cn.devzyh.demo.tree.ds.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树遍历
 */
public class BinaryTreeEach {

    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<>("A");
        root.left = new TreeNode<String>("B");
        root.left.left = new TreeNode<String>("D");
        root.left.right = new TreeNode<String>("E");
        root.right = new TreeNode<String>("C");
        root.right.right = new TreeNode<String>("F");

        List<String> res = new LinkedList<>();
        // 前序遍历
        res.clear();
        beforeEach(root, res);
        res.forEach(System.out::println);

        // 前序遍历迭代实现
        System.out.println("-------------------");
        res = before(root);
        res.forEach(System.out::println);

        System.out.println("===================");

        // 中序遍历
        res.clear();
        middleEach(root, res);
        res.forEach(System.out::println);

        // 中序遍历迭代实现
        System.out.println("-------------------");
        res = middle(root);
        res.forEach(System.out::println);

        System.out.println("===================");

        // 后序遍历
        res.clear();
        afterEach(root, res);
        res.forEach(System.out::println);

        // 后序遍历迭代实现
        System.out.println("-------------------");
        res = after(root);
        res.forEach(System.out::println);


    }

    /**
     * 前序遍历
     *
     * @param root 根节点对象
     * @param res  遍历结果
     */
    static void beforeEach(TreeNode<String> root, List<String> res) {
        // 节点对象为空直接返回
        if (root == null) {
            return;
        }

        // 输出根节点数据
        res.add(root.val);
        // 输出左子树数据
        beforeEach(root.left, res);
        // 输出右子树数据
        beforeEach(root.right, res);
    }

    /**
     * 中序遍历
     *
     * @param root 根节点对象
     * @param res  遍历结果
     */
    static void middleEach(TreeNode<String> root, List<String> res) {
        // 节点对象为空直接返回
        if (root == null) {
            return;
        }

        // 输出左子树数据
        middleEach(root.left, res);
        // 输出根节点数据
        res.add(root.val);
        // 输出右子树数据
        middleEach(root.right, res);
    }

    /**
     * 后序遍历
     *
     * @param root 根节点对象
     * @param res  遍历结果
     */
    static void afterEach(TreeNode<String> root, List<String> res) {
        // 节点对象为空直接返回
        if (root == null) {
            return;
        }

        // 输出左子树数据
        afterEach(root.left, res);
        // 输出右子树数据
        afterEach(root.right, res);
        // 输出根节点数据
        res.add(root.val);
    }

    /**
     * 前序遍历迭代实现
     *
     * @param root
     * @return
     */
    static List<String> before(TreeNode<String> root) {
        List<String> res = new LinkedList<>();
        Deque<TreeNode<String>> stack = new LinkedList<>(); // 存放遍历节点
        stack.push(root);

        while (!stack.isEmpty()) {
            // 输出根节点数据
            root = stack.pop();
            if (root == null) {
                continue;
            }
            res.add(root.val);

            // 先入栈右子树，后输出右子树
            if (root.right != null) {
                stack.push(root.right);
            }


            // 再入栈左子树，先输出右子树
            if (root.left != null) {
                stack.push(root.left);
            }
        }

        return res;
    }

    /**
     * 中序遍历（迭代实现）
     *
     * @param root
     * @return
     */
    static List<String> middle(TreeNode<String> root) {
        List<String> res = new LinkedList<>(); // 存放遍历结果
        Deque<TreeNode<String>> stack = new LinkedList<>(); // 现实模拟栈

        // 树或者栈内有节点就继续遍历
        while (root != null || !stack.isEmpty()) {
            // 根据中序遍历顺序，第一个结点是一棵树的最左边的结点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 出栈并更新目标序列
            root = stack.pop();
            res.add(root.val);

            // 根据中序遍历顺序，根节点之后应该遍历右结点
            root = root.right;
        }
        return res;
    }


    /**
     * 后序遍历迭代实现
     *
     * @param root
     * @return
     */
    static List<String> after(TreeNode<String> root) {
        List<String> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode<String>> stack = new Stack<>();
        stack.push(root);   //首先将根节点压栈
        while (!stack.isEmpty()) {
            TreeNode<String> ele = stack.pop(); //首先出栈的为根节点，其后先出右子节点，后出左子节点
            if (ele.left != null)
                stack.push(ele.left);  //将左子节点压栈
            if (ele.right != null) {
                stack.push(ele.right); //将右子节点压栈
            }
            res.add(0, ele.val); //因为出栈顺序为“根右左”，所以需要每次将元素插入list开头
        }
        return res;
    }

}

