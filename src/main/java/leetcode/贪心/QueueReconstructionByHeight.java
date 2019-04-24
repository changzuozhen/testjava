package leetcode.贪心;

import utils.LogUtils;
import utils.StringBuilderUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E8%B4%AA%E5%BF%83%E6%80%9D%E6%83%B3
 * 根据身高和序号重组队列
 * 406. Queue Reconstruction by Height(Medium)
 * https://leetcode.com/problems/queue-reconstruction-by-height/description/
 * <p>
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * Copy to clipboardErrorCopied
 * 题目描述：一个学生用两个分量 (h, k) 描述，h 表示身高，k 表示排在前面的有 k 个学生的身高比他高或者和他一样高。
 * <p>
 * 为了使插入操作不影响后续的操作，身高较高的学生应该先做插入操作，否则身高较小的学生原先正确插入的第 k 个位置可能会变成第 k+1 个位置。
 * <p>
 * 身高降序、k 值升序，然后按排好序的顺序插入队列的第 k 个位置中。
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        List<int[]> queue = new ArrayList<>();
        for (int[] p : people) {
            queue.add(p[1], p);
            LogUtils.d("reconstructQueue() called with: queue = [" + StringBuilderUtils.fromInt2(queue) + "]");
        }
        return queue.toArray(new int[queue.size()][]);
    }

    public static void main(String[] args) {
        int[][] testData = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        LogUtils.d("main() called with: testData = " + StringBuilderUtils.fromInt2(testData) + "");
        int[][] result = new QueueReconstructionByHeight().reconstructQueue(testData);
        LogUtils.d("main() called with: result = " + StringBuilderUtils.fromInt2(result) + "");
    }
}

