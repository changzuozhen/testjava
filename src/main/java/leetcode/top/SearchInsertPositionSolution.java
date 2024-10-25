package leetcode.top;

/**
 * 11.  搜索插入位置（Search Insert Position）：给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * [题目链接](https://leetcode-cn.com/problems/search-insert-position/)，
 * [题解链接](https://leetcode-cn.com/problems/search-insert-position/solution/sou-suo-cha-ru-wei-zhi-by-leetcode-solution/)
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 */
public class SearchInsertPositionSolution {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        SearchInsertPositionSolution solution = new SearchInsertPositionSolution();
        int[][] testCases = {{1, 3, 5, 6}, {1, 3, 5, 6}, {1, 3, 5, 6}}; // Test cases arrays
        int[] targets = {5, 2, 7}; // Targets for each test case
        int[] expectedResults = {2, 1, 4}; // Expected results for each test case

        for (int i = 0; i < testCases.length; i++) {
            int result = solution.searchInsert(testCases[i], targets[i]);
            assert result == expectedResults[i] : "Test case " + (i + 1) + " failed. Expected " + expectedResults[i] + ", got " + result;
            System.out.println("Test case " + (i + 1) + " passed.");
        }
        System.out.println("All test cases passed.");
    }

}
