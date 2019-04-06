package test_jvm.test_volatile;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseLock {
    public int inc = 0;
    Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        try {
            inc++;
            System.out.println(Thread.currentThread() + "inc:" + inc);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseLock test = new UseLock();
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
