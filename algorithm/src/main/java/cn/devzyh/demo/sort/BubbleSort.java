package cn.devzyh.demo.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * https://www.runoob.com/w3cnote/bubble-sort.html
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = new int[]{22, 34, 3, 32, 82, 55, 89, 50, 37, 5, 64, 35, 9, 70};
        bubbleSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    static void bubbleSort(int[] nums) {
        int tmp;
        // 从左往左循环
        for (int i = 0; i < nums.length; i++) {
            // 循环右侧未处理数据
            for (int j = i + 1; j < nums.length; j++) {
                // 最小值放在左侧
                if (nums[j] < nums[i]) {
                    tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
    }
}
