package leetcode.top;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 14.  最长有效括号（Longest Valid Parentheses）：给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * [题目链接](https://leetcode-cn.com/problems/longest-valid-parentheses/)，
 * [题解链接](https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/)
 * <p>
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号
 * 子串
 * 的长度。
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * <p>
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * <p>
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 * <p>
 * <p>
 * 请给出核心方法的测试用例，并执行输出验证结果，直接给出是否通过测试验证
 * <p>
 * <p>
 * ))())()(())
 */
public class LongestValidParentheseSolution {
    class Solution {
        public int longestValidParentheses(String s) {
            int max = 0;
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i != s.length(); i++) {
                if (s.charAt(i) == '(')
                    stack.push(i);
                else {//')'
                    if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                        stack.poll();//匹配的左括号出栈
                        if (stack.isEmpty()) {
                            //这里是因为栈内元素全部出栈，说明从头开始的序列都是有效的，那么长度就是当前序号+1
                            max = Math.max(max, i + 1);
                        } else {
                            max = Math.max(max, i - stack.peek());
                        }
                    } else {
                        stack.push(i);
                    }
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LongestValidParentheseSolution().new Solution();
        boolean passed = solution.longestValidParentheses("(()") == 2 &&
                solution.longestValidParentheses(")()())") == 4 &&
                solution.longestValidParentheses("") == 0;
        System.out.println("Test passed: " + passed);
    }
}
