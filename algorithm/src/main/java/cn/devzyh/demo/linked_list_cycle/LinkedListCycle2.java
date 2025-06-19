package cn.devzyh.demo.linked_list_cycle;

/**
 * https://leetcode.cn/problems/linked-list-cycle-ii/
 */
public class LinkedListCycle2 {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;

        System.out.println(detectCycle(head).val);
    }

    /**
     * 双指针解法
     * 时间：O(n^2)
     * 空间：O(1)
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        // 空链表处理
        if (head == null) {
            return null;
        }

        // 快慢指针起点稍微错开一下，避免下面相遇判断出问题
        ListNode fastCur = head.next; // 快指针，一次走两步
        ListNode slowCur = head; // 慢指针，一次走一步

        while (fastCur != null && fastCur.next != null) {
            // 判断下两个指针是不是相遇了
            if (fastCur == slowCur) {
                break;
            }

            // 没相遇都走起来
            fastCur = fastCur.next.next; // 快指针一次走两步
            slowCur = slowCur.next; // 慢指针始终一次走一步
        }

        // 不成环直接返回空
        if (fastCur == null || fastCur.next == null) {
            return null;
        }

        // 找出环内离头结点最近的节点就是入环节点
        int minSize = Integer.MAX_VALUE;
        ListNode result = null;

        do {
            // 当前节点到头结点长度
            int size = 0;
            ListNode tmp = head;
            while (tmp != slowCur) {
                size++;
                tmp = tmp.next;
            }

            // 比较是不是最小的
            if (size < minSize) {
                minSize = size;
                result = slowCur;
            }

            // 比较下一个环内节点
            slowCur = slowCur.next;
        } while (slowCur != fastCur);

        return result;
    }

}

