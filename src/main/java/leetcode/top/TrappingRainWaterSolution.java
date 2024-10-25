package leetcode.top;

/**
 * 19.  接雨水（Trapping Rain Water）：给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * [题目链接](https://leetcode-cn.com/problems/trapping-rain-water/)，
 * [题解链接](https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode-solution-tuvc/)
 * <p>
 * <p>
 * 42. 接雨水
 * 困难
 * 相关标签
 * 相关企业
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 10e4
 * 0 <= height[i] <= 10e5
 */
public class TrappingRainWaterSolution {
    public static class Solution {
        public int trap(int[] height) {
            if (height.length <= 2) {
                return 0;
            }
            int leftMax = 0;
            int rightMax = 0;
            int left = 0;
            int right = height.length - 1;
            int res = 0;
            while ((left < right) && (left < height.length) && (right >= 0)) {
                if (height[left] < height[right]) {
                    leftMax = Math.max(leftMax, height[left]);
                    res += leftMax - height[left];
                    left++;
                } else {
                    rightMax = Math.max(rightMax, height[right]);
                    res += rightMax - height[right];
                    right--;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result1 = solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        int result2 = solution.trap(new int[]{4, 2, 0, 3, 2, 5});
        boolean test1Passed = result1 == 6;
        boolean test2Passed = result2 == 9;
        System.out.println("Test 1 passed: " + test1Passed + " | Expected: 6, Actual: " + result1);
        System.out.println("Test 2 passed: " + test2Passed + " | Expected: 9, Actual: " + result2);
    }
}