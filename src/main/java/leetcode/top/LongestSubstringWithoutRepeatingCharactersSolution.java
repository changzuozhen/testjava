package leetcode.top;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 * <p>
 * 3. 无重复字符的最长子串
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
 * 子串
 * 的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class LongestSubstringWithoutRepeatingCharactersSolution {
    static class Solution {
        public int lengthOfLongestSubstring(String s) {

            HashMap<Character, Integer> map = new HashMap<Character, Integer>();
            int max = 0;
            int left = 0;
            int right = 0;
            while (left < s.length() && right < s.length()) {
                char c = s.charAt(right);

                if (map.containsKey(c)) {
                    int index = map.get(c);
                    if (index >= left) {
                        left = index + 1;
                    }
                    map.put(c, right);
                    right++;
                    max = Math.max(max, right - left);
                } else {
                    map.put(c, right);
                    max = Math.max(max, right - left + 1);
                    right++;
                }
            }
            return max;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            boolean case1 = solution.lengthOfLongestSubstring("abcabcbb") == 3;
            boolean case2 = solution.lengthOfLongestSubstring("bbbbb") == 1;
            boolean case3 = solution.lengthOfLongestSubstring("pwwkew") == 3;
            System.out.println("Case 1 passed: " + case1 + " | Output: " + solution.lengthOfLongestSubstring("abcabcbb"));
            System.out.println("Case 2 passed: " + case2 + " | Output: " + solution.lengthOfLongestSubstring("bbbbb"));
            System.out.println("Case 3 passed: " + case3 + " | Output: " + solution.lengthOfLongestSubstring("pwwkew"));
        }
    }
}