package cn.devzyh.demo.queue;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 */
public class ReverseWordsInAString {

    public static void main(String[] args) {
        String s = "hello world!";
        System.out.println(reverseWords(s));

        s = " the sky is blue!";
        System.out.println(reverseWords(s));
    }

    /**
     * 双指针解法
     * 时间：O(n)
     * 空间：O(n)
     *
     * @param s
     * @return
     */
    static String reverseWords(String s) {
        StringBuilder builder = new StringBuilder(); // 存储结果
        int start = s.length() - 1; // 字符开始索引
        int end = -1; // 字符结束索引，-1代表还没读取到字符

        // 倒序遍历字符串
        for (; start >= 0; start--) {
            char c = s.charAt(start);
            // 无单词下出现字符 = 结尾
            if (end == -1 && c != ' ') {
                end = start; // 保留结束位置
            }
            // 有单词下出现空格或到0 = 开始
            if (end != -1 && c == ' ') {
                //找到开始 截取 存储
                builder.append(" ");
                builder.append(s.substring(start + 1, end + 1));
                end = -1; // 重置单词缓存
            }
        }

        // 防止还有字符串没存储
        if (end != -1) {
            builder.append(" ");
            builder.append(s.substring(0, end + 1));
        }

        return builder.substring(1);
    }

}
