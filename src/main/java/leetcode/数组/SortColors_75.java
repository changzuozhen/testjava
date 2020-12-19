package leetcode.数组;

import utils.LogUtils;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/sort-colors/
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortColors_75 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
        LogUtils.d(Arrays.toString(nums));

        nums = new int[]{2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
        LogUtils.d(Arrays.toString(nums));
    }

    public static int[] swap(int[] nums, int a, int b) {
        if (a == b || a >= nums.length || b >= nums.length) return nums;
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
        return nums;
    }

    static class Solution {
        public void sortColors(int[] nums) {
            int zero = -1;// nums[0...zero] == 0
            int two = nums.length;// nums[two...n-1] == 2
            for (int i = 0; i < two; ) {
                if (nums[i] == 0) {
                    zero++;
                    // swap i zero
                    nums[i] = nums[zero];
                    nums[zero] = 0;
                    i++;
                } else if (nums[i] == 2) {
                    two--;
                    // swap i two
                    nums[i] = nums[two];
                    nums[two] = 2;
                } else {
                    i++;
                }
            }
        }
    }
}
