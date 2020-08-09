package tests;

import utils.LogUtils;

import java.util.ArrayList;

class TestSubList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        LogUtils.d("⚠️main() called with: args = [" + list + "]");
        LogUtils.d("⚠️main() called with: args = [" + list.subList(0, 1) + "]");
        LogUtils.d("⚠️main() called with: args = [" + list.subList(1, list.size() - 1) + "]");
        LogUtils.d("⚠️main() called with: args = [" + list.subList(1, list.size() - 2) + "]");
    }
}
