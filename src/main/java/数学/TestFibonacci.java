package 数学;

import utils.LogUtils;

class TestFibonacci {

    /**
     * 斐波那契
     * Fibonacci
     * @param index
     * @return
     */
    public static int calFibonacci(int index) {
        if (index < 2) {
            return 1;
        }
        int a = 1, b = 1, c = 0;
        for (int i = 1; i < index; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            LogUtils.d("⚠️main() called with: Fibonacci(" + i + ") = [" + calFibonacci(i) + "]");
        }
    }
}
