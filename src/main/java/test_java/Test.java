package test_java;

public class Test {

    static class Base {
        public int a = 1;

        public void log() {
            System.out.println("a:" + a);
        }
    }

    static class Derived extends Base {
        public int a = 2;

        public void log() {
            System.out.println("a:" + a);
        }
    }


    public static void main(String... args) {
        Base base = new Derived();
        System.out.println("成员变量是无法实现多态的");
        System.out.println("base.a:" + base.a);
        base.log();
        System.out.println("base.a:" + ((Derived) base).a);
    }
}
