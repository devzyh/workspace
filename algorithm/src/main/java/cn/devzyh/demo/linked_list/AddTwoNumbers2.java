package cn.devzyh.demo.linked_list;

/**
 * https://leetcode.cn/problems/add-two-numbers-ii/
 */
public class AddTwoNumbers2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(-1);
        int[] num1 = new int[]{2, 4, 3};
        ListNode cur1 = l1;
        for (int num : num1) {
            cur1.next = new ListNode(num);
            cur1 = cur1.next;
        }

        ListNode l2 = new ListNode(-1);
        int[] num2 = new int[]{5, 6, 4};
        ListNode cur2 = l2;
        for (int num : num2) {
            cur2.next = new ListNode(num);
            cur2 = cur2.next;
        }

        ListNode result = addTwoNumbers(l1.next, l2.next);
        ListNode curResult = result;
        while (curResult != null) {
            System.out.println(curResult.val);
            curResult = curResult.next;
        }
    }

    // 时间：4 * O(max(m,n)) => O(n)
    // 空间：O(max(m,n)) / O(max(m,n)) + 1 => O(n)
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultHead = new ListNode(-1); // 结果链表
        ListNode resultCur = resultHead;
        // 链表位置
        ListNode cur1 = reverseLink(l1);
        ListNode cur2 = reverseLink(l2);
        int carry = 0; // 进位计数

        while (cur1 != null || cur2 != null) {
            // 取出当前节点数字，不存在取0
            int a = cur1 == null ? 0 : cur1.val;
            int b = cur2 == null ? 0 : cur2.val;

            // 计算数字和
            int sum = a + b + carry;
            // 十位数字
            carry = sum / 10;
            // 个位数字
            int num = sum % 10;

            // 连接到结果链表
            resultCur.next = new ListNode(num);

            // 下一次循环
            cur1 = cur1 == null ? cur1 : cur1.next;
            cur2 = cur2 == null ? cur2 : cur2.next;
            resultCur = resultCur.next;
        }

        // 加完后仍有进位处理
        if (carry > 0) {
            resultCur.next = new ListNode(carry);
        }

        return reverseLink(resultHead.next);
    }

    private static ListNode reverseLink(ListNode head) {
        ListNode rNext = null; // 反转后的下一个节点
        ListNode cur = head; // 当前遍历到的节点
        ListNode rHead = null; // 反转后的头结点
        while (cur != null) {
            ListNode next = cur.next; // 记录翻转前下一个节点位置

            // 链表结束
            if (next == null) {
                rHead = cur;
            }

            // 当前节点作为翻转后的下一个节点
            cur.next = rNext;
            rNext = cur;

            // 迭代增加
            cur = next;
        }

        return rHead;
    }

}

