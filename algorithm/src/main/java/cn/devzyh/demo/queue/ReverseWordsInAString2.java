package cn.devzyh.demo.queue;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string-ii/
 */
public class ReverseWordsInAString2 {

    public static void main(String[] args) {
        char[] chars = new char[]{'t', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e'};
        reverseWords(chars);

        for (char aChar : chars) {
            System.out.print(aChar);
        }
    }

    /**
     * 数组原地解法
     * 时间：O(n^2)
     * 空间：O(1)
     *
     * @param s
     * @return
     */
    static void reverseWords(char[] s) {
        int left = 0;
        int right = s.length - 1;
        // 逆序字符数组
        reverse(s, left, right);

        int start = 0, end = 0;
        while (end < s.length) {
            if (s[end] == ' ') {
                // 逆序单词
                left = start;
                right = end - 1;
                reverse(s, left, right);

                // 重置索引
                start = end + 1;
            }
            end++;
        }

        // 避免遗漏一个单词没逆序
        if (start < end) {
            left = start;
            right = end - 1;
            reverse(s, left, right);
        }
    }

    static void reverse(char[] s, int left, int right) {
        char tmp;
        while (left < right) {
            tmp = s[right];
            s[right] = s[left];
            s[left] = tmp;
            left++;
            right--;
        }
    }

}
