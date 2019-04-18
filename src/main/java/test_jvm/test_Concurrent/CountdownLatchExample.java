package test_jvm.test_Concurrent;

import utils.LogUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            int finalI = i;
            executorService.execute(() -> {
                LogUtils.d("run.." + finalI);
                try {
                    Thread.sleep(finalI * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtils.d("countDown.." + finalI);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        LogUtils.d("end");
        executorService.shutdown();
    }
}
