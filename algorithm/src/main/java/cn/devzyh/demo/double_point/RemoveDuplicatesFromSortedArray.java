package cn.devzyh.demo.double_point;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int result = removeDuplicates2(nums);
        System.out.println(result);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }


    /**
     * 暴力解法
     * 时间：O(n^2)
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates1(int[] nums) {
        // 循环所有数据
        int len = nums.length;
        for (int i = 0; i < len - 1; ) {
            if (nums[i] == nums[i + 1]) {
                // 发现和后一位数据一样则后面所有数据前移一位覆盖重复数据
                for (int j = i + 1; j < len - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                len--; // 覆盖重复数据后，数组长度减一
            } else {
                // 不相同则向后遍历
                i++;
            }
        }
        return len;
    }

    /**
     * 双指针解法
     * 时间：O(n)
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates2(int[] nums) {
        int target = 0; // 目标位置索引
        int source = 1; // 待移动数据索引

        // 循环到待移动索引到达数组边界
        while (source < nums.length) {
            // 目标数据和待移动数据相同
            if (nums[target] == nums[source]) {
                // 待移动索引后移一位
                source++;
            } else {
                // 目标索引后移一位并把移动数据到目标位置
                nums[++target] = nums[source];
            }
        }

        // 目标位置加一就是数据总数
        return target + 1;
    }

}
