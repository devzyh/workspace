package cn.devzyh.demo.greedy;

/**
 * 行相等的最少多米诺旋转
 * https://leetcode.cn/problems/minimum-domino-rotations-for-equal-row/
 */
public class LeetCode1007 {

    public static void main(String[] args) {
        int[] A = new int[]{2, 1, 2, 4, 2, 2};
        int[] B = new int[]{5, 2, 6, 2, 3, 2};
        System.out.println(minDominoRotations1(A, B));
        System.out.println(minDominoRotations2(A, B));
    }

    /**
     * 基本解法
     * 时间：O(n)
     * 空间：O(1)
     */
    static int minDominoRotations1(int[] A, int[] B) {
        int len = A.length; // 骨牌长度
        // 存放骨牌出现次数
        int[] countA = new int[6];
        int[] countB = new int[6];
        int[] countAB = new int[6];

        // 循环骨牌数据
        for (int i = 0; i < len; i++) {
            // 上方出现次数
            countA[A[i] - 1]++;
            countAB[A[i] - 1]++;
            // 下方出现次数
            countB[B[i] - 1]++;
            // 如果上线都出现同样的点数只统计一次
            if (A[i] != B[i]) {
                countAB[B[i] - 1]++;
            }
        }

        // 对骨牌出现次数进行处理
        for (int i = 0; i < countAB.length; i++) {
            // 出现次数与骨牌长度相等
            if (countAB[i] == len) {
                // 返回最大长度的一侧骨牌需要移动的次数
                return len - Math.max(countA[i], countB[i]);
            }
        }

        // 没有满足条件的数据
        return -1;
    }

    /**
     * 贪心算法解法
     * 时间：O(n)
     * 空间：O(1)
     *
     * @return
     */
    static int minDominoRotations2(int[] A, int[] B) {
        int n = A.length;
        //求解A或B全部变成A[0]，最少需要多少次旋转
        int rotations = check(A[0], A, B, n);
        //如果A[0]==B[0], 那么不⽤继续检查B[0]
        //如果A[0]!=B[0] 且可以将A或B中的元素全部变成A[0]，那么也不⽤再检查B[0]
        if (rotations != -1 || A[0] == B[0]) {
            return rotations;
        } else {
            //如果A[0]不满⾜并且A[0]!=B[0]
            // 求解A或B全部变成B[0]，最少需要多少次旋转
            return check(B[0], A, B, n);
        }
    }

    /**
     * 检查将A或者B中元素全部变成x需要多少次旋转
     */
    static int check(int x, int[] A, int[] B, int n) {
        //rotationsA存储将A中元素变成x需要多少次旋转，rotationsB存储将B中元素变成x需要多少次
        int rotationsA = 0, rotationsB = 0;
        //遍历⻣牌判断是否能完成任务（在A中完成或者在B中完成）
        for (int i = 0; i < n; i++) {
            // 如果当前多⽶诺⻣牌上没有数字x，那么不可能完成任务
            if (A[i] != x && B[i] != x) {
                return -1;
            } else if (A[i] != x) {
                // 如果当前多⽶诺⻣牌上A[i]不是x，那么rotationsA需要+1
                rotationsA++;
            } else if (B[i] != x) {
                // 如果当前多⽶诺⻣牌上B[i]不是x，那么rotationsB需要+1
                rotationsB++;
            }
        }
        // 返回最⼩旋转次数
        return Math.min(rotationsA, rotationsB);
    }

}
