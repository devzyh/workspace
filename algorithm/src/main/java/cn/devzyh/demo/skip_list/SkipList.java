package cn.devzyh.demo.skip_list;

/**
 * https://leetcode-cn.com/problems/design-skiplist/
 */
public class SkipList {

    private static final int HEAD_VAL = -1; // 头结点数据
    private Node head; // 跳跃表头结点
    private int levels; // 数据与索引层级数

    /**
     * 构造函数
     */
    public SkipList() {
        head = new Node(HEAD_VAL, null, null);
        levels = 1;
    }

    /**
     * 添加数据
     *
     * @param value
     */
    public void add(int value) {
        Node n = head; // 遍历索引
        Node[] nodes = new Node[levels]; // 记录走过每层的最后一个节点
        int i = 0; // 记录节点数量
        // 遍历查找需要插入数据的位置
        while (n != null) {
            while (n.right != null && n.right.val < value) {
                n = n.right;
            }
            nodes[i++] = n; // 记录当前层最后一个小于插入值的节点
            n = n.down;
        }

        // 添加数据
        n = nodes[--i]; // 获取最后一个元素
        Node add = new Node(value, n.right, null); // 构建新的节点
        n.right = add; // 链接到前一个节点上

        // 添加索引数据
        addIndex(nodes, add);
    }

    /**
     * 添加索引数据
     *
     * @param nodes 每层插入位置前一个节点引用数组
     * @param add   新加入的数据节点
     */
    private void addIndex(Node[] nodes, Node add) {
        Node downNode = add;
        // 在现有索引层内创建索引，从下往上创建，数据层不创建
        for (int i = nodes.length - 2; i >= 0; i--) {
            if (!flipCoin()) {
                // 抛到反面不再添加索引
                return;
            }

            // 当前层添加索引,右侧为上一个节点的右侧，下侧为下层新加入的节点
            Node n = nodes[i];
            Node index = new Node(add.val, n.right, downNode);
            n.right = index;
            downNode = index;
        }

        // 抛硬币决定是否创建新的索引层
        if (flipCoin()) {
            // 创建新索引层节点
            Node index = new Node(add.val, null, downNode); // 新索引层数据节点
            Node indexHead = new Node(HEAD_VAL, index, head); // 新索引层头结点
            // 改变对象头结点指向
            head = indexHead;
            // 数据结构层计数器累加
            levels++;
        }
    }

    /**
     * 抛硬币 true 正面，false 反面
     *
     * @return
     */
    private boolean flipCoin() {
        return Math.random() > 0.5;
    }

    /**
     * 移除数据
     *
     * @param target
     */
    public boolean erase(int target) {
        boolean exist = false; // 保存是否存在这个数据
        Node n = head; // 遍历节点指针

        // 从头结点开始遍历每一层数据
        while (n != null) {
            // 遍历每一层节点，
            while (n.right != null && n.right.val < target) {
                n = n.right; // 遍历右侧相邻节点
            }

            // 如果和右侧节点数据相同，则移除节点
            Node right = n.right;
            if (right != null && right.val == target) {
                exist = true; // 找到了
                n.right = right.right; // 将上一个节点的右侧执行当前节点的右侧
                right.right = null; // 手动去除右侧引用，方便JVM垃圾回收当前节点对象
            }

            // 遍历下一层
            n = n.down;
        }

        return exist;
    }

    /**
     * 检索数据
     *
     * @param target
     * @return
     */
    public boolean search(int target) {
        Node n = head;
        // 从头开始检索每一层数据
        while (n != null) {
            // 检索当前层数据，找到比自己小的最后右侧节点
            while (n.right != null && n.right.val < target) {
                n = n.right;
            }

            // 判断下右节点的值是不是要找的数据
            if (n.right != null && n.right.val == target) {
                return true;
            }

            // 检索下一层
            n = n.down;
        }

        // 检索完数据不存在
        return false;
    }


    /**
     * 链表节点
     */
    class Node {
        int val; // 数据域
        Node right, down; // 向右、向下指针域

        public Node(int val, Node right, Node down) {
            this.val = val;
            this.right = right;
            this.down = down;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("===================================\n");
        Node n = this.head;
        while (n != null) {
            Node head = n;
            while (n != null) {
                builder.append("\t ");
                builder.append(n.val);
                n = n.right;
            }
            builder.append("\n");
            n = head.down;
        }
        builder.append("===================================\n");
        return builder.toString();
    }
}
