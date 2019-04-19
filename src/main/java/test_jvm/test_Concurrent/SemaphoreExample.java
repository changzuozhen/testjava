package test_jvm.test_Concurrent;

import utils.LogUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        final int clientCount = 2;
        final int totalRequestCount = 20;
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            LogUtils.log("i:" + i, i + 4);
        }
        for (int i = 0; i < totalRequestCount; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    LogUtils.log("semaphore acquiere  A i:" + finalI + " ava:" + semaphore.availablePermits(), finalI + 4);
                    semaphore.acquire();

                    LogUtils.log("semaphore acquiered B i:" + finalI + " ava:" + semaphore.availablePermits(), finalI + 4);
//                    Thread.sleep(300);
//                    LogUtils.log("semaphore acquiered C i:" + finalI + " ava:" + semaphore.availablePermits(), finalI + 4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    LogUtils.log("semaphore release   D i:" + finalI + " ava:" + semaphore.availablePermits(), finalI + 4);
                    semaphore.release();
                    LogUtils.log("semaphore release   E i:" + finalI + " ava:" + semaphore.availablePermits(), finalI + 4);
                }
            });
        }
        executorService.shutdown();
    }
}
