package test_jvm;

import utils.LogUtils;

public class TestBreak {
    public static void main(String[] args) {
        out:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (j >= 2) break out;
                LogUtils.d(j);
            }
            LogUtils.d("break");
        }
    }
}


