package leetcode.数组;

import utils.LogUtils;

import java.util.TreeSet;

/**
 * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
 * <p>
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 存在重复元素III_220 {
    public static void main(String[] args) {
//        LogUtils.d("main() called with: result = [" + new Solution().containsNearbyAlmostDuplicate(new int[]{-2147483648, 2147483647}, 1, 1) + "]");
//        LogUtils.d("main() called with: result = [" + new Solution().containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3) + "]");
//        LogUtils.d("main() called with: result = [" + new Solution().containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0) + "]");
        LogUtils.d("main() called with: result = [" + new Solution().containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3) + "]");//false
    }

    static class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            TreeSet<Long> set = new TreeSet<>();
            for (int i = 0; i < nums.length; i++) {
//                if (set.subSet(0L + nums[i] - t, true, 0L + nums[i] + t, true).size() > 0) //	41 ms	41 MB
//                    return true;
                if (set.floor(0L + t + nums[i]) != null && set.floor(0L + t + nums[i]) >= 0L + nums[i] - t) //36 ms	40.2 MB
                    return true;
                set.add(0L + nums[i]);
                if (set.size() > k) {
                    set.remove(0L + nums[i - k]);
                }
            }
            return false;
        }
    }
}
