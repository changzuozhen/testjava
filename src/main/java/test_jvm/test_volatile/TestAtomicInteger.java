package test_jvm.test_volatile;

import utils.LogUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {
    public  AtomicInteger inc = new AtomicInteger();

    public  void increase() {
        inc.getAndIncrement();
        LogUtils.d(Thread.currentThread() + "inc:" + inc);
    }

    public static void main(String[] args) {
        final TestAtomicInteger test = new TestAtomicInteger();
        int currentCount = Thread.activeCount();
        LogUtils.d("Thread.activeCount():" + Thread.activeCount());
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
        LogUtils.d(test.inc); // 100
    }
}
