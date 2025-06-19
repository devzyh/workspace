package cn.devzyh.demo.divide_and_conquer;

/**
 * 搜索二维矩阵
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/
 */
public class Search2dMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[5][];
        matrix[0] = new int[]{1, 4, 7, 11, 15};
        matrix[1] = new int[]{2, 5, 8, 12, 19};
        matrix[2] = new int[]{3, 6, 9, 16, 22};
        matrix[3] = new int[]{10, 13, 14, 17, 24};
        matrix[4] = new int[]{18, 21, 23, 26, 30};
        System.out.println(searchMatrix1(matrix, 33)); // true
    }

    /**
     * 分治算法解法
     * 时间：O(logN)
     * 空间：O(logN)
     *
     * @param matrix
     * @param target
     * @return
     */
    static boolean searchMatrix1(int[][] matrix, int target) {
        // 空行直接返回
        int rows = matrix.length;
        if (rows < 1) {
            return false;
        }

        // 空列直接返回
        int cols = matrix[0].length;
        if (cols < 1) {
            return false;
        }

        // 搜索矩阵数据
        return searchSubMatrix(matrix, target, 0, 0, rows - 1, cols - 1);
    }


    /**
     * 搜索矩阵数据
     *
     * @param matrix   原始矩阵数据
     * @param target   搜索值
     * @param startRow 子矩阵开始行
     * @param startCol 子矩阵开始列
     * @param endRow   子矩阵结束行
     * @param endCol   子矩阵结束列
     * @return
     */
    static boolean searchSubMatrix(int[][] matrix, int target, int startRow, int startCol, int endRow, int endCol) {
        // 递归退出条件
        // 矩阵数据只有一个时 等于目标值 返回true 否则返回false
        if (startRow > endRow || startCol > endCol) {
            return matrix[endRow][endCol] == target;
        }

        // 函数主功能
        // 寻找左上至右下对角线数据，取短的一边作为对角线
        int shortLen = Math.min(endRow - startRow + 1, endCol - startCol + 1);

        // 沿子矩阵对角线进行判断数据，找到子矩阵分割点，舍弃左上和右下区域矩阵（此时两区域内一定不存在目标数据）
        int next = 0; // i为对角线位置循环增量
        for (int i = 0; i < shortLen; i++) { //
            // 刚好在对角线上
            if (matrix[startRow + i][startCol + i] == target) {
                return true;
            }
            // 当前对角线数据小于目标值且下一对角线数据大于目标值则是子矩阵的分割点
            next = i + 1;
            if (matrix[startRow + i][startCol + i] < target && next < shortLen && matrix[startRow + next][startCol + next] > target) {
                break;
            }
        }

        // 递归调用
        // 此时，只有左下和右上子矩阵可能存在数据，对这两个矩阵进行递归搜索
        return searchSubMatrix(matrix, target, startRow + next, startCol, endRow, startCol + next - 1) ||
                searchSubMatrix(matrix, target, startRow, startCol + next, startRow + next - 1, endCol);
    }
}
