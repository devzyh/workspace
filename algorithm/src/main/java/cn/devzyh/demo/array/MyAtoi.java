package cn.devzyh.demo.array;

/**
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 */
public class MyAtoi {

    public static void main(String[] args) {
        MyAtoi myAtoi = new MyAtoi();
        myAtoi.myAtoi1("9223372036854775808");
        String[] nums = new String[]{null, "", "42", "   -42", "4193 with words", "words and 987", "-91283472332", "+1",
                "9223372036854775808", String.valueOf(Integer.MAX_VALUE), String.valueOf(Integer.MIN_VALUE)};
        for (String num : nums) {
            System.out.println("\t" + num + "\t atoi \t" + myAtoi.myAtoi1(num));
        }
    }

    /**
     * 时间：O(n)
     * 空间：O(n)
     */
    public int myAtoi1(String s) {
        // 空输入
        if (s == null) {
            return 0;
        }
        s = s.trim();
        if (s.equals("")) {
            return 0;
        }

        // 去除首位空格后转换为字符数组
        char[] chars = s.toCharArray();

        // 符号处理
        int sign = 1;
        if (chars[0] == '-') {
            sign = -1;
            chars[0] = '0';
        }
        if (chars[0] == '+') {
            chars[0] = '0';
        }

        // 保存提取到的数字
        long result = 0;
        // 遍历字符处理
        for (char c : chars) {
            // 数字的ASCII码在48-57之间
            if (c < 48 || c > 57) {
                break;
            }
            result = result * 10 + (c - 48);
            // 超范围值处理
            if (result >= Integer.MAX_VALUE) {
                break;
            }
        }

        // 超范围值处理
        result = sign * result;
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) result;
    }

}
