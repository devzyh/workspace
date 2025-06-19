package cn.devzyh.demo.linked_list;

/**
 * https://leetcode.cn/problems/add-binary/
 */
public class AddBinary {

    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(addBinary(a, b).toString());

        a = "1010";
        b = "1011";
        System.out.println(addBinary(a, b).toString());

        a = "1111";
        b = "1111";
        System.out.println(addBinary(a, b).toString());

        a = "1111001110001001010101001110001101001100101";
        b = "1101111100100011010110101110111000000110";
        // 10000110001101101100000000000000000001101011
        System.out.println(addBinary(a, b).toString());
    }


    // 时间：O(n)
    // 空间：O(n)
    private static String addBinary(String a, String b) {
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        int maxLength = Math.max(c1.length, c2.length);
        int c1Less = maxLength - c1.length;
        int c2Less = maxLength - c2.length;
        int ZERO_UNICODE = 48; // 48是0的Unicode码

        char[] result = new char[maxLength + 1];
        int carry = 0; // 进位数字

        for (int i = maxLength - 1; i >= 0; i--) {
            int x = i < c1Less ? 0 : c1[i - c1Less] - ZERO_UNICODE;
            int y = i < c2Less ? 0 : c2[i - c2Less] - ZERO_UNICODE;

            int sum = x + y + carry;
            int num = sum % 2; // 取低位
            if (sum > 1) { // 二进制，逢二进一
                carry = 1;
            } else {
                carry = 0; // 不满足进位
            }
            result[i + 1] = (char) (num + ZERO_UNICODE);
        }

        // 结果最高位数据处理
        result[0] = (char) (carry + ZERO_UNICODE);
        if (carry > 0) {
            return new String(result);
        } else {
            return new String(result, 1, result.length - 1);
        }
    }
}
