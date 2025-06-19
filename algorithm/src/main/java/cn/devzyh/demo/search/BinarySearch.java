package cn.devzyh.demo.search;

/**
 * 二分查找
 * http://data.biancheng.net/view/122.html
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 9, 22, 32, 34, 35, 37, 50, 55, 64, 70, 82, 89};
        int target = 4;
        System.out.println(binarySearch(nums, target, 0, nums.length));
    }

    /**
     * 时间：O(logn)
     * @param nums
     * @param target
     * @param start
     * @param end
     * @return
     */
    static int binarySearch(int[] nums, int target, int start, int end) {
        // 搜索完数组未找到数据
        if (start > end) {
            return -1;
        }

        // 取中间位置数据
        int mid = (start + end) / 2;
        // 避免越界
        if (mid >= nums.length) {
            return -1;
        }

        // 相等直接返回
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[mid] < target) {
            // 小于目标数据搜索右侧数组
            return binarySearch(nums, target, mid + 1, end);
        } else {
            // 大于目标数据搜索左侧数组
            return binarySearch(nums, target, start, mid - 1);
        }
    }
}
