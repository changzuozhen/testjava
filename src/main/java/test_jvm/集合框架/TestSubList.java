package test_jvm.集合框架;

import utils.LogUtils;

import java.util.ArrayList;

class TestSubList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        LogUtils.d("⚠️main() called with: args = [" + list + "]"); // [[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]]
        LogUtils.d("⚠️main() called with: args = [" + list.subList(0, 1) + "]"); // [[0]]
        LogUtils.d("⚠️main() called with: args = [" + list.subList(1, list.size() - 1) + "]"); // [[1, 2, 3, 4, 5, 6, 7, 8]]
        LogUtils.d("⚠️main() called with: args = [" + list.subList(1, list.size() - 2) + "]"); // [[1, 2, 3, 4, 5, 6, 7]]
    }
}
