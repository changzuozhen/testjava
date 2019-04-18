package test_jvm.test_threadpool;

import utils.LogUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduledThreadPoolExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);

//        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        //每隔一段时间就触发异常
        exec.scheduleAtFixedRate(() -> {
            //throw new RuntimeException();
            LogUtils.d("===================");

        }, 1000, 5000, TimeUnit.MILLISECONDS);

        //每隔一段时间打印系统时间，证明两者是互不影响的
        exec.scheduleAtFixedRate(() -> LogUtils.d(System.nanoTime()), 1000, 2000, TimeUnit.MILLISECONDS);

    }
}
