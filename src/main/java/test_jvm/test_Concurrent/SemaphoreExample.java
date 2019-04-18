package test_jvm.test_Concurrent;

import utils.LogUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        final int clientCount = 3;
        final int totalRequestCount = 10;
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            LogUtils.log("i:" + i, i + 4);
        }
        for (int i = 0; i < totalRequestCount; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    LogUtils.log("semaphore acquiere   i:" + finalI + " ava:" + semaphore.availablePermits(), finalI + 4);
                    semaphore.acquire();

                    LogUtils.log("semaphore acquiered  i:" + finalI + " ava:" + semaphore.availablePermits(), finalI);
                    Thread.sleep(300);
                    LogUtils.log("semaphore acquiered  i:" + finalI + " ava:" + semaphore.availablePermits(), finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LogUtils.log("semaphore release    i:" + finalI + " ava:" + semaphore.availablePermits(), finalI);
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
    }
}
