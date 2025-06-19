package cn.devzyh.demo.double_point;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/
 */
public class RemoveDuplicatesFromSortedArray2 {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3, 4};
        int result = removeDuplicates(nums);
        System.out.println(result);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }

    /**
     * 双指针解法
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            // 小于两个数的数组直接返回
            return n;
        }

        // 慢指针代表需要保留的数据，快指针代表现在判断的数据
        int slow = 2, fast = 2;
        while (fast < n) {
            // 间隔两位的数字不相同，则当前遍历的这个数据需要保留
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast]; // 将需要保留的数据前移
                ++slow; // 需要保留的数据索引后移一位
            }
            ++fast; // 循环数据
        }
        return slow;
    }


}
