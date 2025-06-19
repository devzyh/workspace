package cn.devzyh.demo.hashtable;

public class LongestSubStringWithAtMostTwoDistinctCharacters {

    public static void main(String[] args) {
        String s = "ccaabbb"; // 5
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));

        s = "eceba"; // 3
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));
    }

    /**
     * 哈希表+双指针
     * 时间：O(n)
     * 空间：O(1)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] table = new int[128]; // 存储数据的出现次数
        int maxSubLen = 0; // 最大子串长度
        int discount = 0; // 字符种类总数
        int left = 0; // 左字符索引
        int right = 0;// 右字符索引

        // 遍历每一个字符
        while (right < s.length()) {
            // 当前字符是否在表内存在
            int rightKey = s.charAt(right); // 哈希表索引
            int rightCount = table[rightKey]; // 索引数据出现次数
            if (rightCount == 0) {
                // 不存在 字符种类+1
                discount++;
            }
            // 右指针字符出现次数+1
            table[rightKey] = rightCount + 1;

            // 判断是否超过两种字符
            if (discount > 2) {
                // 左指针字符出现次数-1
                int leftKey = s.charAt(left);
                int leftCount = table[leftKey];
                leftCount--;
                table[leftKey] = leftCount;

                // 如果左指针字符出现次数归0则字符种类-1
                if (leftCount == 0) {
                    discount--;
                }

                // 左指针前进
                left++;
            } else {
                // 右指针前进
                right++;

                // 计算子串长度
                int subLen = right - left;
                maxSubLen = maxSubLen > subLen ? maxSubLen : subLen;
            }
        }

        return maxSubLen;
    }

}
