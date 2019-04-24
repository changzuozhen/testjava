package test_java;

import utils.LogUtils;

public class Test {

    static class Base {
        public int a = 1;

        public void log() {
            LogUtils.d("a:" + a);
        }
    }

    static class Derived extends Base {
        public int a = 2;

        public void log() {
            LogUtils.d("a:" + a);
        }
    }


    public static void main(String... args) {
        Base base = new Derived();
        LogUtils.d("成员变量是无法实现多态的");
        LogUtils.d("base.a:" + base.a);
        base.log();
        LogUtils.d("base.a:" + ((Derived) base).a);
    }
}
