package tests;

import utils.LogUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadTest implements Callable<Integer> {

    public static void main(String[] args) {
        CallableThreadTest ctt = new CallableThreadTest();
        for (int i = 0; i < 10; i++) {
            LogUtils.d(Thread.currentThread().getName() + " 的循环变量i的值" + i);
            if (i <= 4) {
                FutureTask<Integer> ft = new FutureTask<>(ctt);
                new Thread(ft, "有返回值的 " + i + " 线程").start();
                try {

                    LogUtils.i("子线程的返回值：" + ft.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 10; i++) {
            LogUtils.d(Thread.currentThread().getName() + " -> " + i);
        }
        return i;
    }
}