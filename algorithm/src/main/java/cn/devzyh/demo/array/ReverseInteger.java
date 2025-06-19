package cn.devzyh.demo.array;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        int[] nums = new int[]{123, 321, -123, 120, Integer.MIN_VALUE, Integer.MAX_VALUE, 1534236469};
        for (int num : nums) {
            System.out.println("\t" + num + "\t reverse to \t" + reverseInteger.reverse2(num));
        }
    }

    /**
     * 暴力解法
     * 时间：O(n)
     * 空间：O(n)
     */
    public int reverse1(int x) {
        // 边界值处理
        if (x <= Integer.MIN_VALUE || x >= Integer.MAX_VALUE) {
            return 0;
        }

        // 负数处理，做正数计算，最后变为负数
        int sign = 1;
        if (x < 0) {
            sign = -1;
            x = Math.abs(x);
        }

        // 数字字符串转字符数组
        char[] chars = String.valueOf(x).toCharArray();

        // 倒序填充到新数据组
        char[] newChars = new char[chars.length];
        int maxIndex = chars.length - 1;
        for (int i = maxIndex; i >= 0; i--) {
            newChars[maxIndex - i] = chars[i];
        }

        // 新数组转字符串
        String str = String.valueOf(newChars);

        // 避免反转后出现极大的数
        if (Long.parseLong(str) > Integer.MAX_VALUE) {
            return 0;
        }

        // 字符串转数字并进行符号处理
        return sign * Integer.parseInt(str);
    }

    /**
     * 数组内数据交换解法
     * 时间：O(n)
     * 空间：O(n)
     */
    public int reverse2(int x) {
        // 边界值处理
        if (x <= Integer.MIN_VALUE || x >= Integer.MAX_VALUE) {
            return 0;
        }

        // 负数处理，做正数计算，最后变为负数
        int sign = 1;
        if (x < 0) {
            sign = -1;
            x = Math.abs(x);
        }

        // 数字字符串转字符数组
        char[] chars = String.valueOf(x).toCharArray();

        // 数据位置交换
        int start = 0;
        int end = chars.length - 1;
        char temp;
        // start < end 交换位置没有相遇
        // start = end 偶数个数字，交换位置相遇
        // start > end 奇数个数字，多出一个数字，位于中心，无需交换
        while (start < end) {
            temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }

        // 新数组转字符串
        String str = String.valueOf(chars);

        // 避免反转后出现极大的正数
        if (Long.parseLong(str) > Integer.MAX_VALUE) {
            return 0;
        }

        // 字符串转数字并进行符号处理
        return sign * Integer.parseInt(str);
    }

    /**
     * 数学思维解法
     * 时间：O(n)
     * 空间：O(1)
     */
    public int reverse3(int x) {
        long result = 0; // 保存结果
        int last; // 保存最后一位数
        // x%10取出个位数据，当取模结果等于自己时停止计算
        while ((last = x % 10) != x) {
            // x/10去掉个位数
            x = x / 10;
            // 取到的个位数+上一个数字*10
            result = result * 10 + last;
        }
        // 此时计算数字还差一位，处理的同时判断是否超过int类型范围
        // 在没计算最后一位之前，还差一个量级，肯定不会超过int范围
        result = result * 10L + last;
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) result;
    }
}
