package cn.devzyh.demo.stack;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/remove-outermost-parentheses/
 */
public class RemoveOuterParentheses {

    public static void main(String[] args) {
        String s = "(()())(())";
        System.out.println(removeOuterParentheses1(s));
        System.out.println(removeOuterParentheses2(s));
    }

    /**
     * 暴力解法
     * 时间：O(n)
     * 空间：O(n)
     *
     * @param s
     * @return
     */
    static String removeOuterParentheses1(String s) {
        StringBuilder result = new StringBuilder(); // 结果字符串
        char[] chars = s.toCharArray(); // 原语字符串数组
        int count = 0; // 构成原语条件计数器
        int startIndex = 0; // 原语字符串起始位置

        // 遍历原字符串
        for (int i = 0; i < chars.length; i++) {
            // 原语条件计数器
            if (chars[i] == '(') {
                count++;
            }
            if (chars[i] == ')') {
                count--;
            }
            // 判断是否构成原语
            if (count == 0) {
                // 原语部分移除首位括号添加到结果字符串
                result.append(new String(chars, ++startIndex, i - startIndex));
                startIndex = i + 1; // 下一个原语串起始索引
            }
        }

        // 返回结果字符串
        return result.toString();
    }

    /**
     * 栈解法
     * 时间：O(n)
     * 空间：O(n)
     *
     * @param s
     * @return
     */
    static String removeOuterParentheses2(String s) {
        StringBuilder result = new StringBuilder(); // 结果字符串
        char[] chars = s.toCharArray(); // 原语字符串数组
        Stack<Character> stack = new Stack();

        // 遍历原字符串
        for (int i = 0; i < chars.length; i++) {
            // 左括号入栈
            if (chars[i] == '(') {
                if (!stack.isEmpty()) {
                    // 入栈前发现已经有左括号代表现在这个字符是需要保留的
                    result.append(chars[i]);
                }
                stack.push(chars[i]);
            } else {
                // 左括号出栈同当前的右括号匹配
                stack.pop();
                if (!stack.isEmpty()) {
                    // 出栈后发现还有左括号代表现在这个字符是需要保留的
                    result.append(chars[i]);
                }
            }
        }

        // 返回结果字符串
        return result.toString();
    }
}
