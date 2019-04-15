package test_jvm.test_Concurrent;

/**
 * javac SynchronizedDemo
 * javap -v SynchronizedDemo.class
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {
        }
        method();
    }

    private static void method() {
    }
}

