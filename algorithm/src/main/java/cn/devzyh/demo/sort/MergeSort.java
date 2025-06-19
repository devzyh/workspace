package cn.devzyh.demo.sort;

import java.util.Arrays;

/**
 * 归并排序
 * https://www.runoob.com/w3cnote/merge-sort.html
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] nums = new int[]{22, 34, 3, 32, 82, 55, 89, 50, 37, 5, 64, 35, 9, 70};
        nums = mergeSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    /**
     * 归并排序
     * 时间：O(logn)
     * 空间：O(n)
     *
     * @param nums
     * @return
     */
    static int[] mergeSort(int[] nums) {
        // 元素不大于一个直接返回
        if (nums.length <= 1) {
            return nums;
        }

        // 从中间位置分割数据
        int split = nums.length / 2;

        // 左侧数组进行排序
        int[] left = mergeSort(Arrays.copyOfRange(nums, 0, split));

        // 右侧数组进行排序
        int[] right = mergeSort(Arrays.copyOfRange(nums, split, nums.length));

        // 返回左右数组合并后的数组
        return merge(left, right);
    }

    /**
     * 归并排序的并：对两个有序数组进行合并
     *
     * @param left
     * @param right
     * @return
     */
    static int[] merge(int[] left, int[] right) {
        // 建立一个数组存放结果
        int[] result = new int[left.length + right.length];

        // 双指针分别指向左右数组的遍历位置
        int l = 0, r = 0;

        for (int i = 0; i < result.length; i++) {
            // 其中一个数据遍历完后就不需要继续比较
            if (l < left.length && r < right.length) {
                // 左指针数据小于等于右指针数据 存放左指针当前位置数据并前进
                if (left[l] <= right[r]) {
                    result[i] = left[l++];
                } else { // 左指针数据大于右指针数据 存放右指针当前位置数据并前进
                    result[i] = right[r++];
                }
            } else { // 若有一方数据还没被遍历则直接放入结果数组
                if (l < left.length) {
                    result[i] = left[l++];
                }
                if (r < right.length) {
                    result[i] = right[r++];
                }
            }
        }

        // 返回合并结果
        return result;
    }
}
