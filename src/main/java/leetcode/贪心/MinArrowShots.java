package leetcode.贪心;

import utils.LogUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E8%B4%AA%E5%BF%83%E6%80%9D%E6%83%B3
 * 投飞镖刺破气球
 * 452. Minimum Number of Arrows to Burst Balloons (Medium)
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
 * <p>
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 * <p>
 * Output:
 * 2
 * Copy to clipboardErrorCopied
 * 题目描述：气球在一个水平数轴上摆放，可以重叠，飞镖垂直投向坐标轴，使得路径上的气球都会刺破。求解最小的投飞镖次数使所有气球都被刺破。
 * <p>
 * 也是计算不重叠的区间个数，不过和 Non-overlapping Intervals 的区别在于，[1, 2] 和 [2, 3] 在本题中算是重叠区间。
 */
public class MinArrowShots {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int cnt = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) {
                continue;
            }
            cnt++;
            end = points[i][1];
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[][] demo = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        LogUtils.d("main() called with: findMinArrowShots = [" + new MinArrowShots().findMinArrowShots(demo) + "]");

    }
}
