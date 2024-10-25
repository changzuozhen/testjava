package leetcode.top;

import java.util.Arrays;

/**
 * 16.  在排序数组中查找元素的第一个和最后一个位置（Find First and Last Position of Element in Sorted Array）：给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * [题目链接](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)，
 * [题解链接](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-su-de-di-3/)
 * <p>
 * <p>
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 中等
 * 相关标签
 * 相关企业
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class FindFirstAndLastPositionOfElementInSortedArraySolution {
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[]{-1, -1};
            }
            int left = 0;
            int right = nums.length - 1;
            int mid = 0;
            int start = -1;
            int end = -1;

            while (left <= right) {
                mid = (left + right) / 2;
                if (nums[mid] == target) {
                    start = mid;
                    end = mid;
                    break;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (start == -1) {
                return new int[]{-1, -1};
            } else {
                while (start > 0 && nums[start - 1] == target) {
                    start--;
                }
                while (end < nums.length - 1 && nums[end + 1] == target) {
                    end++;
                }
                return new int[]{start, end};
            }
        }
    }


    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/solutions/504484/zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-su-de-di-3-4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution2 {
        public int[] searchRange(int[] nums, int target) {
            int leftIdx = binarySearch(nums, target, true);
            int rightIdx = binarySearch(nums, target, false) - 1;
            if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
                return new int[]{leftIdx, rightIdx};
            }
            return new int[]{-1, -1};
        }

        public int binarySearch(int[] nums, int target, boolean lower) {
            int left = 0, right = nums.length - 1, ans = nums.length;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] > target || (lower && nums[mid] >= target)) {
                    right = mid - 1;
                    ans = mid;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArraySolution().new Solution();
        // Test case 1
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int[] result1 = solution.searchRange(nums1, target1);
        System.out.println(Arrays.equals(result1, new int[]{3, 4}) ? "Test case 1 passed" : "Test case 1 failed");

        // Test case 2
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        int[] result2 = solution.searchRange(nums2, target2);
        System.out.println(Arrays.equals(result2, new int[]{-1, -1}) ? "Test case 2 passed" : "Test case 2 failed");

        // Test case 3
        int[] nums3 = {};
        int target3 = 0;
        int[] result3 = solution.searchRange(nums3, target3);
        System.out.println(Arrays.equals(result3, new int[]{-1, -1}) ? "Test case 3 passed" : "Test case 3 failed");
    }

}