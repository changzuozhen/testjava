package leetcode.top;

/**
 * 20.  Pow(x, n)（Pow(x, n)）：实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * [题目链接](https://leetcode-cn.com/problems/powx-n/)，
 * [题解链接](https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/)
 * <p>
 * 50. Pow(x, n)
 * 中等
 * 相关标签
 * 相关企业
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * <p>
 * 提示：
 * <p>
 * -100.0 < x < 100.0
 * -2e31 <= n <= 2e31-1
 * n 是一个整数
 * 要么 x 不为零，要么 n > 0 。
 * -10e4 <= xn <= 10e4
 */
public class PowNSolution {
    static class Solution {
        public double myPow(double x, int n) {
            double res = 1.0;
            long m = n;
            if (m < 0) {
                m = -m;
                x = 1 / x;
            }
            while (m > 0) {
                if ((m & 1) == 1) {
                    res *= x;
                }
                x *= x;
                m >>= 1;
            }
            return res;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            boolean testsPassed = true;
            double result;

            // Test case 1
            result = solution.myPow(2.00000, 10);
            testsPassed &= Math.abs(result - 1024.00000) < 0.00001;
            System.out.println("Test 1 passed: " + (Math.abs(result - 1024.00000) < 0.00001) + ", Result: " + result);

            // Test case 2
            result = solution.myPow(2.10000, 3);
            testsPassed &= Math.abs(result - 9.26100) < 0.00001;
            System.out.println("Test 2 passed: " + (Math.abs(result - 9.26100) < 0.00001) + ", Result: " + result);

            // Test case 3
            result = solution.myPow(2.00000, -2);
            testsPassed &= Math.abs(result - 0.25000) < 0.00001;
            System.out.println("Test 3 passed: " + (Math.abs(result - 0.25000) < 0.00001) + ", Result: " + result);

            System.out.println("All tests passed: " + testsPassed);
        }
    }

    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/powx-n/solutions/238559/powx-n-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution2 {
        public double myPow(double x, int n) {
            long N = n;
            return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
        }

        public double quickMul(double x, long N) {
            double ans = 1.0;
            // 贡献的初始值为 x
            double x_contribute = x;
            // 在对 N 进行二进制拆分的同时计算答案
            while (N > 0) {
                if (N % 2 == 1) {
                    // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                    ans *= x_contribute;
                }
                // 将贡献不断地平方
                x_contribute *= x_contribute;
                // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
                N /= 2;
            }
            return ans;
        }
    }
}