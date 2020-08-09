package tests;

import utils.LogUtils;

class TestFabonacci {

    public static int calFabonaci(int index) {
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
            LogUtils.d("⚠️main() called with: Fabonaci(" + i + ") = [" + calFabonaci(i) + "]");
        }
    }
}
