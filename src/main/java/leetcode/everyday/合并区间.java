package leetcode.everyday;

/**
 * 合并区间
 * 给出一组区间，请合并所有重叠的区间。
 * 请保证合并后的区间按区间起点升序排列。
 *
 * 数据范围：区间组数 ，区间内 的值都满足
 * 要求：空间复杂度 ，时间复杂度
 * 进阶：空间复杂度 ，时间复杂度
 * 示例 1
 *
 *
 * 输入
 * [[10,30],[20,60],[80,100],[150,180]]
 * 输出
 * [[10,60],[80,100],[150,180]]
 * 示例 2
 *
 *
 * 输入
 * [[0,10],[10,20]]
 * 输出
 * [[0,20]]
 * 题目解析
 * 解法1：对所有区间按左端点排序，然后遍历时维护右端点最大值即可。
 * 解法2：对值域差分后做前缀和，然后遍历一遍值域即可得到所有区间。
 *
 *
 */


import java.util.*;

public class 合并区间 {
    public class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        // 对所有区间按左端点排序
        intervals.sort((a, b) -> a.start - b.start);

        ArrayList<Interval> merged = new ArrayList<>();
        Interval prev = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);

            // 如果当前区间的左端点小于等于前一个区间的右端点，说明有重叠
            if (curr.start <= prev.end) {
                // 更新前一个区间的右端点为当前区间和前一个区间的右端点的较大值
                prev.end = Math.max(prev.end, curr.end);
            } else {
                // 没有重叠，将前一个区间加入结果列表，并更新前一个区间为当前区间
                merged.add(prev);
                prev = curr;
            }
        }

        // 将最后一个区间加入结果列表
        merged.add(prev);

        return merged;
    }

    public static void main(String[] args) {
        合并区间 solution = new 合并区间();

        // 创建测试用例
        ArrayList<合并区间.Interval> intervals = new ArrayList<>();
        intervals.add(solution.new Interval(10, 30));
        intervals.add(solution.new Interval(20, 60));
        intervals.add(solution.new Interval(80, 100));
        intervals.add(solution.new Interval(150, 180));

        // 调用方法进行合并
        ArrayList<合并区间.Interval> merged = solution.merge(intervals);

        // 打印合并后的结果
        for (合并区间.Interval interval : merged) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }
}