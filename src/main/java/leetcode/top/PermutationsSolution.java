package leetcode.top;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 18.  全排列（Permutations）：给定一个没有重复数字的序列，返回其所有可能的全排列。
 * [题目链接](https://leetcode-cn.com/problems/permutations/)，
 * [题解链接](https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/)
 * <p>
 * 46. 全排列
 * 中等
 * 相关标签
 * 相关企业
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class PermutationsSolution {
    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                integers.add(nums[i]);
            }
            permute(integers, 0, result);
            return result;
        }

        private void permute(ArrayList<Integer> nums, int startLock, List<List<Integer>> result) {
            if (startLock == nums.size()) {
                result.add(new ArrayList<>(nums));
                return;
            }
            for (int i = startLock; i < nums.size(); i++) {
                Collections.swap(nums, startLock, i);
                permute(nums, startLock + 1, result);
                Collections.swap(nums, startLock, i);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] test;
        List<List<Integer>> res;

        test = new int[]{1, 2, 3};
        res = solution.permute(test);
        System.out.println("Test case 1: " + res.equals(Arrays.asList(
                Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), Arrays.asList(2, 1, 3),
                Arrays.asList(2, 3, 1), Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1)
        )) + " " + res);

        test = new int[]{0, 1};
        System.out.println("Test case 2: " + solution.permute(test).equals(Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(1, 0)
        )));

        test = new int[]{1};
        System.out.println("Test case 3: " + solution.permute(test).equals(Collections.singletonList(
                Collections.singletonList(1)
        )));
    }

}