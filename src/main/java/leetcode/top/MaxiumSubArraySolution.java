package leetcode.top;

/**
 * 12.  最大子序和（Maximum Subarray）：给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * [题目链接](https://leetcode-cn.com/problems/maximum-subarray/)，
 * [题解链接](https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/)
 * <p>
 * <p>
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组
 * 是数组中的一个连续部分。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 */
public class MaxiumSubArraySolution {
    public int maxSubArray(int[] nums) {
        int tmpMax = 0;
        int max = Integer.MIN_VALUE;
        for (int i : nums) {
            tmpMax = Math.max(tmpMax + i, i);
            max = Math.max(max, tmpMax);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxiumSubArraySolution solution = new MaxiumSubArraySolution();
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test case 1: Expected output: 6, Actual output: " + solution.maxSubArray(nums1));

        int[] nums2 = {1};
        System.out.println("Test case 2: Expected output: 1, Actual output: " + solution.maxSubArray(nums2));

        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println("Test case 3: Expected output: 23, Actual output: " + solution.maxSubArray(nums3));
    }
}
