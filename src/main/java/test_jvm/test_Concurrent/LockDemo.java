package test_jvm.test_Concurrent;

import utils.LogUtils;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(500);
                    LogUtils.d("i:" + finalI);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }
}
