package test_jvm.test_Concurrent.顺序执行;
import java.util.concurrent.Semaphore;

/**
 * 我们使用了三个信号量 semaphoreT1、semaphoreT2 和 semaphoreT3 来控制线程的执行顺序。
 * 初始时，semaphoreT1 的信号量为 1，而 semaphoreT2 和 semaphoreT3 的信号量为 0。
 * 线程 T1 先执行任务，并在任务完成后释放 semaphoreT2 的信号量，唤醒线程 T2。
 * 线程 T2 在获取到 semaphoreT2 的信号量后执行任务，并在任务完成后释放 semaphoreT3 的信号量，唤醒线程 T3。
 * 线程 T3 在获取到 semaphoreT3 的信号量后执行任务。
 * 通过使用信号量来控制线程的执行顺序，可以确保 T1、T2 和 T3 按照指定的顺序执行。
 */
public class TestSemaphore {
    private static Semaphore semaphoreT1 = new Semaphore(1);
    private static Semaphore semaphoreT2 = new Semaphore(0);
    private static Semaphore semaphoreT3 = new Semaphore(0);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                // T1 执行任务
                System.out.println("T1 is executing");
                Thread.sleep(1000);
                System.out.println("T1 finished executing");

                // 释放信号量，唤醒 T2
                semaphoreT2.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                // 等待 T1 完成
                semaphoreT2.acquire();

                // T2 执行任务
                System.out.println("T2 is executing");
                Thread.sleep(1000);
                System.out.println("T2 finished executing");

                // 释放信号量，唤醒 T3
                semaphoreT3.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                // 等待 T2 完成
                semaphoreT3.acquire();

                // T3 执行任务
                System.out.println("T3 is executing");
                Thread.sleep(1000);
                System.out.println("T3 finished executing");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}