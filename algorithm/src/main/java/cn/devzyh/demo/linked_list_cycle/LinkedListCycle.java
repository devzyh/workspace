package cn.devzyh.demo.linked_list_cycle;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/linked-list-cycle/
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;

        System.out.println(hasCycle1(head));

        System.out.println(hasCycle2(head));
    }

    /**
     * 暴力解法
     * 时间：O(n^2)
     * 空间：O(n)
     *
     * @param head
     * @return
     */
    public static boolean hasCycle1(ListNode head) {
        ListNode tmp = head;
        List<ListNode> nodes = new LinkedList<>();
        while (tmp != null) {
            if (nodes.contains(tmp)) {
                return true;
            } else {
                nodes.add(tmp);
            }
            tmp = tmp.next;
        }
        return false;
    }

    /**
     * 双指针解法
     * 时间：O(n)
     * 空间：O(1)
     *
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head) {
        // 空链表处理
        if (head == null) {
            return false;
        }

        // 快慢指针起点稍微错开一下，避免下面相遇判断出问题
        ListNode fastCur = head.next; // 快指针，一次走两步
        ListNode slowCur = head; // 慢指针，一次走一步

        while (fastCur != null && fastCur.next != null) {
            // 判断下两个指针是不是相遇了
            if (fastCur == slowCur) {
                return true;
            }

            // 没相遇都走起来
            fastCur = fastCur.next.next; // 快指针一次走两步
            slowCur = slowCur.next; // 慢指针始终一次走一步
        }
        return false;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
