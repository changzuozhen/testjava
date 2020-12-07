package leetcode.数组;

import utils.LogUtils;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 长度最小的子数组_209 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        LogUtils.d(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    static class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            int l = 0;
            int r = -1; // nums[l...r] 为滑动窗口
            int sum = 0;
            int res = nums.length + 1;
            while (r < nums.length) {
                if (sum >= s) {
                    sum -= nums[l++];
                } else if (++r < nums.length) {
                    sum += nums[r];
                } else {
                    break;
                }
                if (sum >= s) {
                    res = Math.min(r - l + 1, res);
                }
            }
            if (res == nums.length + 1) return 0;
            return res;
        }
    }
}
