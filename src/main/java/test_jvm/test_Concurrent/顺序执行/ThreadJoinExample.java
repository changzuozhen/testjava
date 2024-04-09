package test_jvm.test_Concurrent.顺序执行;

/**
 * 线程 T1 执行完任务后，线程 T2 使用 t1.join() 方法等待 T1 完成，然后执行自己的任务。
 * 线程 T3 使用 t2.join() 方法等待 T2 完成，然后执行自己的任务。
 * 通过使用 join() 方法，可以确保线程按照指定的顺序执行。
 */
public class ThreadJoinExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            // T1 执行任务
            System.out.println("T1 is executing");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1 finished executing");
        });

        Thread t2 = new Thread(() -> {
            try {
                // 等待 T1 完成
                t1.join();

                // T2 执行任务
                System.out.println("T2 is executing");
                Thread.sleep(1000);
                System.out.println("T2 finished executing");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                // 等待 T2 完成
                t2.join();

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
