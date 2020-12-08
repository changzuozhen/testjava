package leetcode.数组;

import utils.LogUtils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 说明：
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 两个数组的交集_349 {
    public static void main(String[] args) {
        两个数组的交集_349.Solution solution = new 两个数组的交集_349.Solution();
        LogUtils.d(Arrays.toString(solution.intersection(new int[]{2, 3, 1, 2, 4, 3}, new int[]{2, 3, 1, 2, 4, 3})));
    }

    static class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null) {
                return null;
            }
            HashSet<Integer> set1 = new HashSet<>();
            HashSet<Integer> set2 = new HashSet<>();
            for (int i : nums1) {
                set1.add(i);
            }
            for (int i : nums2) {
                if (set1.contains(i)) {
                    set2.add(i);
                }
            }
//            return set2.stream().mapToInt(Integer::intValue).toArray();
            int[] result = new int[set2.size()];
            int start = 0;
            for (int item : set2) {
                result[start] = item;
                start++;
            }
            return result;
        }
    }

}
