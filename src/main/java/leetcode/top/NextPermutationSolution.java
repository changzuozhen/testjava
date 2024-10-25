package leetcode.top;

import java.util.Arrays;

/**
 * 13.  下一个排列（Next Permutation）：实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * [题目链接](https://leetcode-cn.com/problems/next-permutation/)，
 * [题解链接](https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/)
 * <p>
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * <p>
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * <p>
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * <p>
 * 9 8 7 6 5 4 3 2 1
 * 1 2 3 4 5 6 7 8 9
 * <p>
 * 2 9 8 7 6 5 4 3 1
 * 3 9 8 7 6 5 4 2 1
 * 3 1 2 4 5 6 7 8 9
 */
public class NextPermutationSolution {


    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int curIndex = nums.length - 2;

        while (curIndex >= 0) {
            if (nums[curIndex] < nums[curIndex + 1]) {
                break;
            }
            curIndex--;
        }
        int swapIndex = nums.length - 1;
        if (curIndex >= 0) {
            while (swapIndex > curIndex) {
                if (nums[swapIndex] > nums[curIndex]) {
                    break;
                }
                swapIndex--;
            }
            swap(nums, curIndex, swapIndex);
        }
        reverse(nums, curIndex + 1);

    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        NextPermutationSolution solution = new NextPermutationSolution();
        int[] nums1 = {1, 2, 3};
        solution.nextPermutation(nums1);
        System.out.println(Arrays.equals(nums1, new int[]{1, 3, 2}) ? "Test case 1 passed." : "Test case 1 failed.");

        int[] nums2 = {3, 2, 1};
        solution.nextPermutation(nums2);
        System.out.println(Arrays.equals(nums2, new int[]{1, 2, 3}) ? "Test case 2 passed." : "Test case 2 failed.");

        int[] nums3 = {1, 1, 5};
        solution.nextPermutation(nums3);
        System.out.println(Arrays.equals(nums3, new int[]{1, 5, 1}) ? "Test case 3 passed." : "Test case 3 failed.");
    }


    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/next-permutation/solutions/479151/xia-yi-ge-pai-lie-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[i] >= nums[j]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i + 1);
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public void reverse(int[] nums, int start) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
    }
}
