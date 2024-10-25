package leetcode.top;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 17.  组合总和（Combination Sum）：给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * [题目链接](https://leetcode-cn.com/problems/combination-sum/)，
 * [题解链接](https://leetcode-cn.com/problems/combination-sum/solution/zu-he-zong-he-by-leetcode-solution/)
 * <p>
 * 39. 组合总和
 * 中等
 * 相关标签
 * 相关企业
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 * <p>
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 * <p>
 * 输入: candidates = [2], target = 1
 * 输出: []
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 */
public class CombinationSumSolution {

    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            if (candidates == null || candidates.length == 0) {
                return new ArrayList<>();
            }
            Arrays.sort(candidates);
            ArrayList<List<Integer>> result = new ArrayList<>();
            combinationSum(candidates, target, 0, new ArrayList<>(), result);
            return result;
        }

        public void combinationSum(int[] candidates, int target, int candidatesIndex, List<Integer> sumList, List<List<Integer>> result) {
            if (candidatesIndex >= candidates.length || candidates[candidatesIndex] > target) {
                return;
            }
            if (candidates[candidatesIndex] == target) {
                sumList.add(candidates[candidatesIndex]);
                result.add(new ArrayList<>(sumList));
                sumList.remove(sumList.size() - 1);
                return;
            }

            sumList.add(candidates[candidatesIndex]);
            combinationSum(candidates, target - candidates[candidatesIndex], candidatesIndex, sumList, result);
            sumList.remove(sumList.size() - 1);

            combinationSum(candidates, target, candidatesIndex + 1, sumList, result);
        }
    }

    public static void main(String[] args) {
        Solution solution = new CombinationSumSolution().new Solution();
        int[] candidates;
        int target;
        List<List<Integer>> res;


        candidates = new int[]{2, 3, 6, 7};
        target = 7;
        res = solution.combinationSum(candidates, target);
        System.out.println("Test case 1: " + (res.toString().equals("[[2, 2, 3], [7]]") ? "Passed" : "Failed" + res.toString()));

        candidates = new int[]{2, 3, 5};
        target = 8;
        res = solution.combinationSum(candidates, target);
        System.out.println("Test case 2: " + (res.toString().equals("[[2, 2, 2, 2], [2, 3, 3], [3, 5]]") ? "Passed" : "Failed" + res.toString()));

        candidates = new int[]{2};
        target = 1;
        res = solution.combinationSum(candidates, target);
        System.out.println("Test case 3: " + (res.isEmpty() ? "Passed" : "Failed" + res.toString()));

        candidates = new int[]{8, 7, 4, 3};
        target = 11;
        res = solution.combinationSum(candidates, target);
        System.out.println("Test case 4: " + (res.toString().equals("[[3, 4, 4], [3, 8], [4, 7]]") ? "Passed" : "Failed" + res.toString()));
    }

}