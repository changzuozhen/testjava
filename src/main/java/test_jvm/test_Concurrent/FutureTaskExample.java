package test_jvm.test_Concurrent;

import utils.LogUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 在介绍 Callable 时我们知道它可以有返回值，返回值通过 Future 进行封装。FutureTask 实现了 RunnableFuture 接口，
 * 该接口继承自 Runnable 和 Future 接口，这使得 FutureTask 既可以当做一个任务执行，也可以有返回值。
 */
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                LogUtils.log("begin", 1);
                int result = 0;
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(10);
                    result += i;
                }
                LogUtils.log("end:" + result, 1);
                return result;
            }
        });

        Thread computeThread = new Thread(futureTask, "thread 1");
        computeThread.start();

        Thread otherThread = new Thread(() -> {
            LogUtils.log("other task is running...", 3);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtils.log("other task is Done", 3);
        }, "thread 2");
        otherThread.start();
        LogUtils.log(futureTask.get(), 6);
    }
}
