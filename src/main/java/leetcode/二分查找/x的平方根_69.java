package leetcode.二分查找;

import utils.LogUtils;

/**
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class x的平方根_69 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 0; i < 20; i++) {
            LogUtils.d("⚠️ " + i + "->" + solution.mySqrt(i));
        }
//        int i = 2;
//        LogUtils.d("⚠️ " + i + "->" + solution.mySqrt(i));
    }
}

class Solution {
    public int mySqrt(int x) {
        int l = 0, h = x, ans = -1;
        while (l <= h) {
            long mid = l + (h - l) / 2;
            if (mid * mid <= x) {
                ans = (int) mid;
                l = (int) mid + 1;
            } else {
                h = (int) mid - 1;
            }
        }
        return ans;
    }
}