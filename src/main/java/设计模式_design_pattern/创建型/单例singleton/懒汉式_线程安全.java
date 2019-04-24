package 设计模式_design_pattern.创建型.单例singleton;

/**
 * Ⅲ 懒汉式-线程安全
 * 只需要对 getUniqueInstance() 方法加锁，那么在一个时间点只能有一个线程能够进入该方法，从而避免了实例化多次 uniqueInstance。
 * <p>
 * 但是当一个线程进入该方法之后，其它试图进入该方法的线程都必须等待，即使 uniqueInstance 已经被实例化了。这会让线程阻塞时间过长，因此该方法有性能问题，不推荐使用。
 */
public class 懒汉式_线程安全 {
    private static Singleton3 uniqueInstance;

    public static synchronized Singleton3 getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton3();
        }
        return uniqueInstance;
    }
}

class Singleton3 {

}
