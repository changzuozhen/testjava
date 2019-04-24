package utils;

import static utils.LogUtils.colorIndex;

public class TestLogUtils {
    public static void main(String... args) {
        LogUtils.v("----v----");
        LogUtils.d("----d----");
        LogUtils.i("----i----");
        LogUtils.w("----w----");
        LogUtils.e("----e----");
        for (int i = 0; i < colorIndex.length; i++) {
            LogUtils.log("i:" + i, i);
        }
    }
}
