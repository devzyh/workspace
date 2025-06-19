package cn.devzyh.demo.sort;

import java.util.Arrays;

/**
 * 快速排序
 * http://www.biancheng.net/algorithm/quick_sort.html
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] nums = new int[]{35, 33, 42, 10, 14, 19, 27, 44, 26, 31};
        quickSort(nums, 0, nums.length - 1);
        Arrays.stream(nums).forEach(System.out::println);
    }

    static void quickSort(int[] nums, int p, int q) {
        if (q - p <= 0) {
            // 此时已经递归到只有一个元素
            return;
        } else {

            // 选出基准值对区域内的数据进行大小分类
            int m = partition(nums, p, q);
            quickSort(nums, p, m - 1); // 递归左侧数据
            quickSort(nums, m + 1, q); // 递归右侧数据
        }
    }

    /**
     * 对区域内的数据归类，并返回基准值位置
     *
     * @param nums
     * @param p
     * @param q
     * @return
     */
    static int partition(int[] nums, int p, int q) {
        int base = q--; // 区域内最后一个数字作为基准值
        int tmp;

        while (true) {
            // 左侧数据不大于基准数据则p前进
            while (nums[p] < nums[base]) {
                p++;
            }

            // 右侧数据不小于基准数据则q前进
            while (q > 0 && nums[q] > nums[base]) {
                q--;
            }

            // 左侧索引与右侧索引相遇
            if (p >= q) {
                break;
            }

            // 否则交换p、q指向的数据
            tmp = nums[p];
            nums[p] = nums[q];
            nums[q] = tmp;
            // 各前进一步，减少无效比较
            p++;
            q--;
        }

        // 将基准数据交换到中间位置
        tmp = nums[p];
        nums[p] = nums[base];
        nums[base] = tmp;

        return p; // 返回基准数据的位置
    }
}
