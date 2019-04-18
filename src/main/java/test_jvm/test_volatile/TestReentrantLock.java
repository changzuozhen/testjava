package test_jvm.test_volatile;

import utils.LogUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    public int inc = 0;
    Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        try {
            inc++;
            LogUtils.d(Thread.currentThread() + "inc:" + inc);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final TestReentrantLock test = new TestReentrantLock();
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
