package 设计模式_design_pattern.创建型.单例singleton;

/**
 * Ⅴ 静态内部类实现
 * 当 Singleton5 类加载时，静态内部类 SingletonHolder 没有被加载进内存。只有当调用 getUniqueInstance() 方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载，此时初始化 INSTANCE 实例，并且 JVM 能确保 INSTANCE 只被实例化一次。
 * <p>
 * 这种方式不仅具有延迟初始化的好处，而且由 JVM 提供了对线程安全的支持。
 */
public class 静态内部类实现 {
}

class Singleton5 {

    private Singleton5() {
    }

    private static class SingletonHolder {
        private static final Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }
}