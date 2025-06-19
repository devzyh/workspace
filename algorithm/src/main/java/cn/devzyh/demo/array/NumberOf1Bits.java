package cn.devzyh.demo.array;

/**
 * https://leetcode-cn.com/problems/number-of-1-bits/
 */
public class NumberOf1Bits {

    public static void main(String[] args) {
        int[] nums = new int[]{
                0b00000000000000000000000000001011,
                0b00000000000000000000000010000000,
                0b11111111111111111111111111111101
        };
        NumberOf1Bits numberOf1Bits = new NumberOf1Bits();
        for (int n : nums) {
            System.out.printf("nums:%d 1bits:%d \n", n, numberOf1Bits.hammingWeight1(n));
        }
    }

    /**
     * 暴力解法
     * 时间：O(n)
     * 空间：O(n)
     *
     * @param n
     * @return
     */
    public int hammingWeight1(int n) {
        char[] chars = Integer.toBinaryString(n).toCharArray();
        int count = 0;
        for (char c : chars) {
            if (c == '1') {
                count++;
            }
        }
        return count;
    }
}
