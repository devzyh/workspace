package cn.devzyh.demo.sort;

import java.util.Arrays;

/**
 * 插入排序
 * https://www.runoob.com/w3cnote/insertion-sort.html
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] nums = new int[]{22, 34, 3, 32, 82, 55, 89, 50, 37, 5, 64, 35, 9, 70};
        insertionSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

        static void insertionSort(int[] nums) {
            int tmp;
            // 从第二个元素开始循环
            for (int i = 1; i < nums.length; i++) {
                // 与左侧数据对比，比他小则前移
                for (int j = i; j > 0; j--) {
                    if (nums[j] > nums[j - 1]) {
                        break;
                    }
                    tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }
            }
        }
}
