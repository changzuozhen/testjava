package test_jvm;

/**
 * javac Base.java
 * javap -verbose Base
 * 1. 构造方法、实例方法的第一个参数是this！这是由编译器自动添加的。
 * 2. this引用变量的数据类型是，this所在方法的所属类。即，编码时，this出现在哪个类中，this的数据类型就是这个类。
 * 3. 既然编译器会自动给实例方法添加一个this参数，那么就不难理解，当调用某个实例对象的方法时，编译器会将该实例对象当做参数传递到调用方法中了。
 */

class Base {
    public String name = "I am Base";

    void instanceMethod(String str1) {
        //do something
    }

    static void staticMethod(Base base, String str1) {
        //do something
    }

    static void classMethod(String str2) {
        //do something
    }
}

public class TestGC {
    public static void main(String[] args) {
        String str = "showGCStrategy";
        while (true) {
            str += str + str;
            str.intern();  // 强制产生垃圾
        }
    }
}
