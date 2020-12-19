package leetcode.max_area;

import utils.LogUtils;

/**
 * 695. Max Area of Island (Easy)
 * https://leetcode.com/problems/max-area-of-island/description/
 * 查找最大的连通面积
 */

public class TestMaxArea {

    private int m, n;
    private int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        m = grid.length;
        n = grid[0].length;

        int maxArea = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                maxArea = Math.max(maxArea, dfs(grid, i, j));

        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0)
            return 0;

        grid[r][c] = 0;
        int area = 1;
        for (int[] d : direction)
            area += dfs(grid, r + d[0], c + d[1]);
//        for (int i = 0; i < direction.length; i++) {
//            area += dfs2(grid, r + direction[i][0], c + direction[i][1]);
//        }
        return area;
    }

    public static void main(String[] args) {
        TestMaxArea tool = new TestMaxArea();
        int[][] area = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        LogUtils.d("max:" + tool.maxAreaOfIsland(area));
    }

}
