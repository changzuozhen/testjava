package test_jvm.基础语法;

import utils.LogUtils;

/**
 * 静态属性和静态方法是否可以被继承？是否可以被重写？以及原因。
 * 静态属性和静态方法可以被继承，但是没有被重写而是被隐藏。
 * <p>
 * 静态方法和属性是属于类的，调用的时候直接通过 类名.方法名 完成，不需要继承机制就可以调用。如果子类里面定义了静态方法和属性，那么这时候父类的静态方法或属性称之为“隐藏”。至于是否继承一说，子类是有继承静态方法和属性，但是跟实例方法和属性不太一样，存在隐藏这种情况。
 * <p>
 * 多态之所以能够实现依赖于继承、接口和重写、重载（继承和重写最为关键）。有了继承和重写就可以实现父类的引用指向不同子类的对象。 重写的功能是：重写后子类的优先级高于父类的优先级，但是隐藏是没有这个优先级之分的。
 * <p>
 * 静态属性、静态方法和非静态的属性都可以被继承和隐藏而不能被重写，因此不能实现多态，不能实现父类的引用可以指向不同子类的对象。
 * <p>
 * 非静态方法可以被继承和重写，因此可以实现多态。
 * https://github.com/Omooo/Android_QA/blob/master/Answer.md#18--%E9%9D%99%E6%80%81%E5%B1%9E%E6%80%A7%E5%92%8C%E9%9D%99%E6%80%81%E6%96%B9%E6%B3%95%E6%98%AF%E5%90%A6%E5%8F%AF%E4%BB%A5%E8%A2%AB%E7%BB%A7%E6%89%BF%E6%98%AF%E5%90%A6%E5%8F%AF%E4%BB%A5%E8%A2%AB%E9%87%8D%E5%86%99%E4%BB%A5%E5%8F%8A%E5%8E%9F%E5%9B%A0
 */
public class TestExtends {

    public static void main(String... args) {
        Base base = new Derived();

        LogUtils.d("成员变量是无法实现多态的"); // 成员变量是无法实现多态的
        LogUtils.d("Base base.a:" + base.a); // Base base.a:1
        LogUtils.d("Base base.s:" + base.s); // Base base.s:1
        LogUtils.d("Derived ((Derived) base).a:" + ((Derived) base).a); // Derived ((Derived) base).a:2
        LogUtils.d("Derived ((Derived) base).s:" + ((Derived) base).s); // Derived ((Derived) base).s:2
        LogUtils.d("成员方法有多态，静态方法需要指定 类型才可以调用到特定的方法"); // 成员方法有多态，静态方法需要指定 类型才可以调用到特定的方法
        base.log(); // Derived log a:2 ,s:2
        base.slog(); // Base slog s:1
        ((Derived) base).log(); // Derived log a:2 ,s:2
        ((Derived) base).slog(); // Derived slog s:2
//        HashMap3<String, String> hashMap = new HashMap3<String, String>();
//        for (int i = 0; i < 20; i++) {
//            hashMap.put("" + i, "a" + i);
//        }

    }

    static class Base {
        public static int s = 1;
        public int a = 1;

        public static void slog() {
            LogUtils.d("Base slog s:" + s);
        }

        public void log() {
            LogUtils.d("Base log a:" + a + " ,s:" + s);
        }
    }

    static class Derived extends Base {
        public static int s = 2;
        public int a = 2;

        public static void slog() {
            LogUtils.d("Derived slog s:" + s);
        }

        public void log() {
            LogUtils.d("Derived log a:" + a + " ,s:" + s);
        }
    }
}
