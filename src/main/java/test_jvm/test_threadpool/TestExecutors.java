package test_jvm.test_threadpool;

import utils.LogUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutors {
    public static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            LogUtils.d(Thread.currentThread().getName() + "正在执行.... MyName:" + getName());
        }
    }

    public static void main(String[] args) {
        //创建一个可重用固定线程数的线程池
//        ExecutorService pool = Executors.newSingleThreadExecutor();
        //创建一个可重用固定线程数的线程池
//        ExecutorService pool=Executors.newFixedThreadPool(2);
        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newCachedThreadPool();

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口;
        Runnable t1 = new MyThread("1");
        Runnable t2 = new MyThread("2");
        Runnable t3 = new MyThread("3");
        Runnable t4 = new MyThread("4");
        Runnable t5 = new MyThread("5");
        //将线程放到池中执行；
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        // 关闭线程池
        pool.shutdown();
    }
}
