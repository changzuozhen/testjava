package leetcode.top;

/**
 * 15.  搜索旋转排序数组（Search in Rotated Sorted Array）：假设按照升序排序的数组在预先未知的某个点上进行了旋转。请你搜索 nums 中是否存在这个目标值 target。如果存在，则返回它的索引，否则返回 -1 。
 * [题目链接](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)，
 * [题解链接](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/sou-suo-xuan-zhuan-pai-xu-shu-zu-by-leetcode-solut/)
 * <p>
 * 33. 搜索旋转排序数组
 * 中等
 * 相关标签
 * 相关企业
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 */
public class SearchInRotatedSortedArraySolution {
    class Solution {
        public int search(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            int offset = 0;
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (inOrder(nums, left, mid)) {
                    //有序： left,mid
                    if (withIn(nums, left, mid, target)) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    //有序： mid+1,right
                    if (withIn(nums, mid + 1, right, target)) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
            }
            return -1;
        }

        private boolean inOrder(int[] nums, int left, int right) {
            if (nums[left] <= nums[right]) {
                return true;
            } else {
                return false;
            }
        }

        private boolean withIn(int[] nums, int left, int right, int target) {
            return nums[left] <= target && nums[right] >= target;
        }
    }

    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArraySolution().new Solution();
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0) == 4 ? "Test passed" : "Test failed");
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 7) == 3 ? "Test passed" : "Test failed");
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 1) == 5 ? "Test passed" : "Test failed");
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3) == -1 ? "Test passed" : "Test failed");
        System.out.println(solution.search(new int[]{1}, 0) == -1 ? "Test passed" : "Test failed");
    }

}
