package test_jvm.test_Concurrent.顺序执行;

import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        System.out.println("创建线程池");
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<?> t1 = executorService.submit(() -> {
            // T1 执行任务
            System.out.println("T1 is executing");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1 finished executing");
        });

        Future<?> t2 = executorService.submit(() -> {
            try {
                // 等待 T1 完成
//                t1.get();

                // T2 执行任务
                System.out.println("T2 is executing");
                Thread.sleep(1000);
                System.out.println("T2 finished executing");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Future<?> t3 = executorService.submit(() -> {
            try {
                // 等待 T2 完成
//                t2.get();

                // T3 执行任务
                System.out.println("T3 is executing");
                Thread.sleep(1000);
                System.out.println("T3 finished executing");
            } catch (InterruptedException  e) {
                e.printStackTrace();
            }
        });

        // 关闭线程池
        executorService.shutdown();
        System.out.println("关闭线程池");
    }
}
