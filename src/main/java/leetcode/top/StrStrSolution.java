package leetcode.top;

/**
 * 10.  实现 strStr()（Implement strStr()）：给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
 * [题目链接](https://leetcode-cn.com/problems/implement-strstr/)，
 * [题解链接](https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode-solution-ds6y/)
 * <p>
 * 28. 找出字符串中第一个匹配项的下标
 * 简单
 * 相关标签
 * 相关企业
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 示例 2：
 * <p>
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 */
public class StrStrSolution {

    static class MySolution {
        public int strStr(String haystack, String needle) {
            if (needle.length() == 0) {
                return 0;
            }
            if (needle.length() > haystack.length()) {
                return -1;
            }
            int n = haystack.length();
            int m = needle.length();
            OUT:
            for (int i = 0; i <= n - m; i++) {
                for (int j = 0; j < m; j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        continue OUT;
                    }
                }
                return i;
            }
            return -1;
        }

        public static void main(String[] args) {
            MySolution solution = new MySolution();

            // Test case 1
            String haystack1 = "sadbutsad", needle1 = "sad";
            int result1 = solution.strStr(haystack1, needle1);
            System.out.println("Test case 1: " + (result1 == 0 ? "Passed" : "Failed") + ", Result: " + result1);

            // Test case 2
            String haystack2 = "leetcode", needle2 = "leeto";
            int result2 = solution.strStr(haystack2, needle2);
            System.out.println("Test case 2: " + (result2 == -1 ? "Passed" : "Failed") + ", Result: " + result2);

            // Additional test cases
            String haystack3 = "hello", needle3 = "ll";
            int result3 = solution.strStr(haystack3, needle3);
            System.out.println("Additional test case 1: " + (result3 == 2 ? "Passed" : "Failed") + ", Result: " + result3);

            String haystack4 = "aaaaa", needle4 = "bba";
            int result4 = solution.strStr(haystack4, needle4);
            System.out.println("Additional test case 2: " + (result4 == -1 ? "Passed" : "Failed") + ", Result: " + result4);
        }
    }
}
