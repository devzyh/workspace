package cn.devzyh.demo.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/number-of-recent-calls/
 */
public class NumberOfRecentCalls {


    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 100, 3001, 3002};

        RecentCounter1 r1 = new RecentCounter1();
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(r1.ping(nums1[i]));
        }

        RecentCounter2 r2 = new RecentCounter2();
        for (int i = 1; i <= 10; i++) {
            System.out.println(r2.ping(i));
        }

        RecentCounter3 r3 = new RecentCounter3();
        for (int i = 1; i <= 10000; i++) {
            System.out.println(r3.ping(i));
        }

    }
}

/**
 * 暴力解法
 * 时间：O(n)
 * 空间：O(n)
 */
class RecentCounter1 {

    private int[] data;
    private int lastIndex;

    public RecentCounter1() {
        data = new int[10000]; // 最多请求10^4次，此处有点浪费空间
        lastIndex = 0; // 最后一位数字索引
    }

    public int ping(int t) {
        // 存入新数字
        data[lastIndex++] = t;

        // 记录符合条件的数字总数
        int count = 0;
        for (int i = 0; i < lastIndex; i++) {
            if (data[i] >= (t - 3000)) {
                count++;
            }
        }
        return count;
    }
}

/**
 * 双指针解法
 * 时间：O(n)
 * 空间：O(1)
 */
class RecentCounter2 {

    private int[] data; // 保存请求历史
    private int head; // 头索引
    private int tail; // 尾索引

    public RecentCounter2() {
        data = new int[3002]; // 最多只用存储3001个历史数据
        head = tail = 0;
    }

    public int ping(int t) {
        // 尾部存入数据
        data[tail++] = t;
        if (tail == data.length) {
            // 加到数组最后一位则从头开始加
            tail = 0;
        }

        // 头部排除无效历史数据
        while (data[head] < t - 3000) {
            head++;
            // 找到数组最后一位则从头开始找
            if (head == data.length) {
                head = 0;
            }
        }

        // 头尾差即为符合条件的总数
        if (head > tail) {
            return data.length - (head - tail);
        } else {
            return tail - head;
        }
    }
}

/**
 * 队列解法
 * 时间：O(n)
 * 空间：O(1)
 */
class RecentCounter3 {

    private Queue<Integer> data; // 保存有效的请求数据

    public RecentCounter3() {
        data = new LinkedList<>();
    }

    public int ping(int t) {
        // 存入当前请求数据
        data.add(t);

        // 移除无效请求数据
        while (data.peek() < t - 3000) {
            data.poll();
        }

        // 返回有效数据长度
        return data.size();
    }
}
