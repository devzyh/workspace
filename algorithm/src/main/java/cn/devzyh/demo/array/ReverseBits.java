package cn.devzyh.demo.array;

/**
 * https://leetcode-cn.com/problems/reverse-bits/
 */
public class ReverseBits {

    public static void main(String[] args) {
        int[] nums = new int[]{
                0b00000010100101000001111010011100,
                0b11111111111111111111111111111101
        };
        ReverseBits reverseBits = new ReverseBits();
        for (int n : nums) {
            System.out.printf("nums:%d reverseBits:%d \n", n, reverseBits.reverseBits1(n));
        }
    }

    /**
     * 官方解法
     *
     * @param n
     * @return
     */
    public int reverseBits1(int n) {
        int res = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            res |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return res;
    }
}
