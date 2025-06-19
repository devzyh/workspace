package cn.devzyh.demo.linked_list;

/**
 * https://leetcode.cn/problems/add-two-numbers/
 */
public class AddTwoNumbers {

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

    // 时间：O(max(m,n)) => O(n)
    // 空间：O(max(m,n)) / O(max(m,n)) + 1 => O(n)
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultHead = new ListNode(-1); // 结果链表
        ListNode resultCur = resultHead;
        // 链表位置
        ListNode cur1 = l1;
        ListNode cur2 = l2;
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

        return resultHead.next;
    }

}

/**
 * 链表
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
