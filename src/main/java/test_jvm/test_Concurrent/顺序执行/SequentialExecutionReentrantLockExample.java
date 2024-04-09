package test_jvm.test_Concurrent.顺序执行;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 我们使用了两个条件变量 conditionT2 和 conditionT3 来控制线程的等待和唤醒。
 * 同时，我们引入了两个布尔变量 t2Executed 和 t3Executed 来记录线程 T2 和 T3 是否已经执行。
 * 线程 T1 先获取锁并执行任务，然后设置 t2Executed 为 true，并通过 conditionT2.signal() 唤醒线程 T2。
 * 线程 T2 在获取到锁后，通过 conditionT2.await() 进行等待，直到 t2Executed 为 true。
 * 然后执行任务，设置 t3Executed 为 true，并通过 conditionT3.signal() 唤醒线程 T3。
 * 线程 T3 在获取到锁后，通过 conditionT3.await() 进行等待，直到 t3Executed 为 true。
 * 然后执行任务。
 */

public class SequentialExecutionReentrantLockExample {
    private static Lock lock = new ReentrantLock();
    private static Condition conditionT2 = lock.newCondition();
    private static Condition conditionT3 = lock.newCondition();
    private static boolean t2Executed = false;
    private static boolean t3Executed = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                // T1 执行任务
                lock.lock();
                System.out.println("T1 is executing");
                Thread.sleep(1000);
                System.out.println("T1 finished executing");

                // 设置 t2Executed 为 true，唤醒 T2
                t2Executed = true;
                conditionT2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                // 等待 T1 完成
                lock.lock();
                while (!t2Executed) {
                    conditionT2.await();
                }

                // T2 执行任务
                System.out.println("T2 is executing");
                Thread.sleep(1000);
                System.out.println("T2 finished executing");

                // 设置 t3Executed 为 true，唤醒 T3
                t3Executed = true;
                conditionT3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                // 等待 T2 完成
                lock.lock();
                while (!t3Executed) {
                    conditionT3.await();
                }

                // T3 执行任务
                System.out.println("T3 is executing");
                Thread.sleep(1000);
                System.out.println("T3 finished executing");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}