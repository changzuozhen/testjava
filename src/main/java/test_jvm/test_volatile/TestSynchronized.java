package test_jvm.test_volatile;

public class TestSynchronized {
    public int inc = 0;

    public synchronized void increase() {
        inc++;
        System.out.println(Thread.currentThread() + "inc:" + inc);
    }

    public static void main(String[] args) {
        final TestSynchronized test = new TestSynchronized();
        int currentCount = Thread.activeCount();
        System.out.println("Thread.activeCount():" + Thread.activeCount());
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++)
                    test.increase();
            }).start();
        }

        while (Thread.activeCount() > currentCount) {
            //保证前面的线程都执行完
            Thread.yield();
        }
        System.out.println(test.inc);
    }
}
