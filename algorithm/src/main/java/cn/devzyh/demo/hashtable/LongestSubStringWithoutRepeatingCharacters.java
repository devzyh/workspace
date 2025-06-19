package cn.devzyh.demo.hashtable;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubStringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthOfLongestSubstring3(str));
    }

    /**
     * 暴力解法
     * 时间：O(n^3)
     * 空间：O(n)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        int len = s.length();
        if (len < 2) {
            // 字符串长度不超过1的直接返回
            return len;
        }

        // 转换为数组方便操作
        char[] chars = s.toCharArray();
        int maxSubLen = 1; // 最大符合要求子串长度

        // 循环查找子字符串是否符合要求
        for (int start = 0; start < len - 1; start++) {
            for (int end = start + 1; end < len; end++) {
                // 查找子字符串的最后一个字符是否在子串内存在
                boolean noRepeat = true;
                for (int i = start; i < end; i++) {
                    if (chars[i] == chars[end]) {
                        noRepeat = false;
                        break;
                    }
                }

                // 不存在则与最大子串长度对比
                if (noRepeat) {
                    // 子字符串长度
                    int subLen = end - start + 1;
                    if (subLen > maxSubLen) {
                        maxSubLen = subLen;
                    }
                } else {
                    break; // 当前的子字符串已经重复了，后面的子字符串必然重复，就没必要对比了
                }
            }
        }

        return maxSubLen;
    }


    /**
     * 哈希表+双指针解法
     * 时间：O(n)
     * 空间：O(1)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int maxSubLen = 0; // 最长不重复子串长度
        int left = 0; // 左指针位置
        int right = 0; // 右指针位置
        char[] chars = new char[128]; // 哈希数据数组

        // 遍历每个字符
        while (right < s.length()) {
            // 对数据进行哈希处理
            char rightChar = s.charAt(right);
            int key = hashKey(rightChar, chars.length); // 哈希算法计算位置
            // 数据是否在哈希表内存在
            if (chars[key] == rightChar) {
                // 存在从哈希表里删除当前字符左指针后移
                chars[key] = '\u0000'; // 空字符
                left++;
            } else {
                // 不存在把当前字符放进哈希表，右指针后移
                chars[key] = rightChar;
                right++;

                // 计算子串长度，并保存最大的长度
                int subLen = right - left;
                maxSubLen = maxSubLen > subLen ? maxSubLen : subLen;
            }
        }

        return maxSubLen;
    }

    /**
     * 对数据进行哈希处理
     *
     * @param c
     * @param len
     * @return
     */
    private static int hashKey(char c, int len) {
        return ((int) c) % len;
    }

    /**
     * 哈希表+双指针优化解法
     * 时间：O(n)
     * 空间：O(1)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int maxSubLen = 0; // 最长不重复子串长度
        int left = 0; // 左指针位置
        int right = 0; // 右指针位置
        int[] table = new int[128]; // 哈希数据数组，存储最后一次出现的索引
        // 初始化哈希数组填充不可能存在的值
        for (int i = 0; i < table.length; i++) {
            table[i] = -1;
        }

        // 遍历每个字符
        while (right < s.length()) {
            int key = s.charAt(right); // 哈希算法计算位置
            // 数据是否在哈希表内存在
            if (table[key] != -1) {
                // 存在则将左指针移动到最后一次出现位置的后面
                int lastInedx = table[key] + 1;
                // 避免左指针回退
                left = lastInedx > left ? lastInedx : left;
            }

            // 记录右指针指向的位置
            table[key] = right;
            right++; // 遍历下一个字符

            // 计算子串长度，并保存最大的长度
            int subLen = right - left;
            maxSubLen = maxSubLen > subLen ? maxSubLen : subLen;

        }

        return maxSubLen;
    }

}
