package cn.devzyh.demo.tree.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/
 */
public class KSmallestPairs {

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 序列类型排序器
     */
    class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            // 对比两个序列数据的和
            return o1.x + o1.y - o2.x - o2.y;
        }
    }

    /**
     * 获取k对最小序列和
     * 时间：O(n^2)
     * 空间：O(m) m=n1+n2
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 有一个序列为空直接返回空
        if (nums1.length == 0 || nums2.length == 0) {
            return null;
        }

        List<Pair> pairs = new LinkedList<>();
        // 遍历每一组序列
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                pairs.add(new Pair(nums1[i], nums2[j]));
            }
        }

        // 对数组排序
        pairs.sort(new PairComparator());

        // 返回前k对最小序列
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < Math.min(k, nums1.length * nums2.length); i++) {
            List<Integer> nums = new ArrayList<>(2);
            Pair p = pairs.get(i);
            nums.add(p.x);
            nums.add(p.y);
            res.add(nums);
        }

        return res;
    }

    public static void main(String[] args) {

        int[] nums1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] nums2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 10;

        KSmallestPairs kSmallestPairs = new KSmallestPairs();
        List<List<Integer>> res = kSmallestPairs.kSmallestPairs(nums1, nums2, k);
        res.forEach(System.out::println);
    }
}
